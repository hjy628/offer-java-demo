package com.hjy.utils;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.Data;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;


/**
 * @auther: hjy
 * @Date: 2024/3/20 14:12
 * @Description:
 */

public class PdfUtils {


    public static void main(String[] args) {
        try {
            PdfDataModel pdfDataModel = new PdfDataModel();
            pdfDataModel.setText1("511300T15163");//条形码
            pdfDataModel.setText2("xxxxx业务员自费账户");//送检单位
            pdfDataModel.setText3("xxxx");//姓名
            pdfDataModel.setText4("男");//性别
            pdfDataModel.setText14("27");//年龄
            pdfDataModel.setText5("xxxxxxxxxxxxxxxxxxxx");//身份证号
            pdfDataModel.setText6("送检医师");//送检医师
            pdfDataModel.setText7("病 员 号");//病 员 号
            pdfDataModel.setText8("科    别");//科    别
            pdfDataModel.setText9("xxxxxxxxx");//病人电话
            pdfDataModel.setText10("咽拭子");//标本类型
            pdfDataModel.setText11("肉眼未见异常");//标本性状
            pdfDataModel.setText12("2022-04-27 17:35 ");//采样时间
            pdfDataModel.setText13("2022-04-27 17:35 ");
            pdfDataModel.setText15("xxx xxx Ao xxx Laboratory Results Report");//英文主题
            pdfDataModel.setText22("xxxxxxxxxx检验实验室检验报告单");//汉语主题
            pdfDataModel.setText23("此结果仅为2019-nCoV新型冠状病毒核酸定性筛查； \n" +
                    "本检测结果所使用的检测方法为实时荧光RT-PCR技术，最低检测限: 500 拷贝/mL\n" +
                    "，检测新型冠状病毒（2019-nCoV）的ORF1ab和编码核衣壳蛋白基因N。");//建议与解释：
            pdfDataModel.setText16("xxxxx");//备 注
            pdfDataModel.setText17("LABxxxxxxxx");//院方条形码
            pdfDataModel.setText18("www.xxxxx.com");//网站地址:
            pdfDataModel.setText19("xxxxxxxxxxxxxxxxxxxxxxxxxx");//地址：
            pdfDataModel.setText20("xxxxxxxxxx/xxxxxxxxx(转602)");//客服电话：
            pdfDataModel.setText21(" 2022-11-2301:58");//报告日期

            //图片
            PdfDataImgaModel pdfDataImgaModel = new PdfDataImgaModel();
            pdfDataImgaModel.setImage1("doc/1.png");//左上角图片
            pdfDataImgaModel.setImage2("doc/2.jpeg");//专用章图片
            pdfDataImgaModel.setImage3("doc/3.png");//右下角图片
            pdfDataImgaModel.setImage5("doc/4.png");//检验者
            pdfDataImgaModel.setImage6("doc/5.png");//审核者
            pdfDataImgaModel.setImage7("doc/6.png");//批准人
            pdfDataImgaModel.setImage4("doc/7.png");//条形码图片

            List<TestItemsModel> testItemsModels = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                TestItemsModel testItemsModel = new TestItemsModel();
                testItemsModel.setTitle("新型冠状病毒核酸检测(10合1)(2019-nCoV)" + i);
                testItemsModel.setResult("阴性");
                testItemsModel.setTag("阴性");
                testItemsModel.setUnit("单位");
                testItemsModels.add(testItemsModel);
            }
            ByteArrayOutputStream outputStream = getPdfUrl(testItemsModels, pdfDataModel, pdfDataImgaModel);

            FileOutputStream fos = new FileOutputStream("./doc/re.pdf");
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            bos.write(outputStream.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 检测报告
     * @param testItemsModels
     * @param pdfDataModel
     * @param pdfDataImgaModel
     * @return
     */
    public static ByteArrayOutputStream getPdfUrl(List<TestItemsModel> testItemsModels, PdfDataModel pdfDataModel, PdfDataImgaModel pdfDataImgaModel) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            BaseFont chinese = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            ByteArrayOutputStream outputStream = generateTempPDF("./doc/pdf_template.pdf", chinese, pdfDataModel, pdfDataImgaModel);
            List<PdfReader> pdfReaderList = new ArrayList<>();
            if (!CollectionUtils.isEmpty(testItemsModels)) {
                byte[] bytes = outputStream.toByteArray();
                List<List<TestItemsModel>> splitList = splitList(testItemsModels, 20);
                for (List<TestItemsModel> itemsModels : splitList) {
                    //填充数据
                    PdfReader pdfReader = new PdfReader(filleTestItemsData(chinese, itemsModels, bytes).toByteArray());
                    pdfReaderList.add(pdfReader);
                }
            } else {
                PdfReader pdfReader = new PdfReader(outputStream.toByteArray());
                pdfReaderList.add(pdfReader);
            }
            mergePdfFiles(pdfReaderList, byteArrayOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream;
    }

    /**
     * 填充检测数据
     *
     * @param chinese
     * @param testItemsModels
     * @param bytes
     * @throws IOException
     * @throws DocumentException
     */
    private static ByteArrayOutputStream filleTestItemsData(BaseFont chinese, List<TestItemsModel> testItemsModels, byte[] bytes) throws IOException, DocumentException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfReader reader = new PdfReader(bytes);
        Rectangle rectangle = reader.getPageSize(1);
        Document doc = new Document(rectangle, 50, 50, 50, 50);
        PdfWriter writer = PdfWriter.getInstance(doc, byteArrayOutputStream);
        doc.open();
        doc.newPage();
        PdfContentByte cb = writer.getDirectContent();
        PdfImportedPage page = writer.getImportedPage(reader, 1);
        cb.addTemplate(page, 0, 0);
        //首页追加信息
        PdfContentByte directContent = writer.getDirectContent();
        directContent.beginText();
        directContent.setFontAndSize(chinese, 10);
        directContent.setColorFill(BaseColor.BLACK);
        for (int i = 1; i <= testItemsModels.size() - 1; i++) {
            TestItemsModel testItemsModel = testItemsModels.get(i - 1);
            directContent.showTextAligned(Element.ALIGN_LEFT, testItemsModel.getTitle(), 20, 630 - (i * 20), 0);
            directContent.showTextAligned(Element.ALIGN_LEFT, testItemsModel.getResult(), 280, 630 - (i * 20), 0);
            directContent.showTextAligned(Element.ALIGN_LEFT, testItemsModel.getTag(), 380, 630 - (i * 20), 0);
            directContent.showTextAligned(Element.ALIGN_LEFT, testItemsModel.getUnit(), 520, 630 - (i * 20), 0);
        }
        directContent.endText();
        doc.close();
        return byteArrayOutputStream;
    }

    public static ByteArrayOutputStream generateTempPDF(String fileName, BaseFont chinese, PdfDataModel pdfDataModel, PdfDataImgaModel pdfDataImgaModel) {
        PdfReader reader = null;
        PdfStamper ps = null;
        ByteArrayOutputStream bos = null;
        try {
            reader = new PdfReader(fileName);
            bos = new ByteArrayOutputStream();
            ps = new PdfStamper(reader, bos);
            //填充文字
            AcroFields fields = ps.getAcroFields();
            fillData(fields, beanToMap(pdfDataModel), chinese);//渲染
            //填充图片
            Map<String, String> imageMap = beanToMap(pdfDataImgaModel);
            // 处理图片
            for (String key : imageMap.keySet()) {
                String value = imageMap.get(key);
                String imgpath = value;
                if (!org.springframework.util.StringUtils.isEmpty(imgpath)) {
                    int pageNo = fields.getFieldPositions(key).get(0).page;
                    Rectangle signRect = fields.getFieldPositions(key).get(0).position;
                    float x = signRect.getLeft();
                    float y = signRect.getBottom();
                    // 根据路径读取图片
                    Image image = Image.getInstance(imgpath);
                    // 获取图片页面
                    PdfContentByte under = ps.getOverContent(pageNo);
                    // 图片大小自适应
                    image.scaleToFit(signRect.getWidth(), signRect.getHeight());
                    // 设置图片位置，以为我们以左下角为起始点，所以这里x、y加上偏移量，偏移量为计算的居中量
                    image.setAbsolutePosition(x + (signRect.getWidth() - image.getScaledWidth()) / 2, y + (signRect.getHeight() - image.getScaledHeight()) / 2);
                    // 添加图片
                    under.addImage(image);
                }
            }
            //必须要调用这个，否则文档不会生成的
            ps.setFormFlattening(true);
            if (ps != null) {
                ps.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bos != null) {
                    bos.close();
                }
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bos;
    }

    /**
     * PDF文件合并
     *
     * @param pdfReaderList
     * @author
     */
    public static boolean mergePdfFiles(List<PdfReader> pdfReaderList, ByteArrayOutputStream outputStream) {
        boolean retValue = false;
        Document document = null;
        try {
            document = new Document();
            PdfCopy copy = new PdfCopy(document, outputStream);
            document.open();
            for (PdfReader reader : pdfReaderList) {
                int n = reader.getNumberOfPages();
                for (int j = 1; j <= n; j++) {// 一个文件有多少页循环
                    document.newPage();
                    PdfImportedPage page = copy.getImportedPage(reader, j);
                    copy.addPage(page);
                }
            }
            retValue = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
        return retValue;
    }

    /**
     * 填充模板中的数据
     */
    public static void fillData(AcroFields fields, Map<String, String> data, BaseFont chinese) {
        try {
            // 默认字体
            float fontSize = 10.0f;
            for (String key : data.keySet()) {
                String value = data.get(key);
                if (!StringUtils.isEmpty(value)) {
                    // 文本框宽度
                    Rectangle position = fields.getFieldPositions(key).get(0).position;
                    float textBoxWidth = position.getWidth();
                    // 文本框高度
                    float textBoxHeight = position.getHeight();
                    float ascent = chinese.getFontDescriptor(chinese.ASCENT, fontSize);
                    // baseFont渲染后的文字宽度
                    float textWidth = chinese.getWidthPoint(value, fontSize);
                    // 文本框高度只够写一行，并且文字宽度大于文本框宽度，则缩小字体
                    if (textBoxHeight < ascent * 1.6) {
                        while (textWidth > textBoxWidth) {
                            fontSize--;
                            textWidth = chinese.getWidthPoint(value, fontSize);
                        }
                    }
                    fields.setFieldProperty(key, "textsize", 10.0f, null);
                    if (key.equalsIgnoreCase("Text15")) {
                        fields.setFieldProperty(key, "textsize", 13.0f, null);
                    }
                    if(!key.equalsIgnoreCase("Text15")
                            || !key.equalsIgnoreCase("Text22")
                            || !key.equalsIgnoreCase("Text18")
                            || !key.equalsIgnoreCase("Text19")
                            || !key.equalsIgnoreCase("Text20")  ){
                        fields.setFieldProperty(key, "textfont", chinese, null);
                    }

                    // 为字段赋值,注意字段名称是区分大小写的
                    fields.setField(key, value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static <T> List<List<T>> splitList(List<T> list, int splitSize) {
        //判断集合是否为空
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }

        //计算分割后的大小
        int maxSize = (list.size() + splitSize - 1) / splitSize;
        //开始分割
        return Stream.iterate(0, n -> n + 1)
                .limit(maxSize)
                .parallel()
                .map(a -> list.parallelStream().skip(a * splitSize).limit(splitSize).collect(Collectors.toList()))
                .filter(b -> !b.isEmpty())
                .collect(Collectors.toList());
    }

    /**
     * 一张A4打印多个面单
     * @param orginPdfList
     * @param outputPdfFile
     * @throws Exception
     */
    public static void mergePdf(java.util.List<String> orginPdfList, String outputPdfFile) throws Exception {
        Document doc = new Document(PageSize.A4);
        PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(outputPdfFile));
        writer.setPageEvent(new PdfPageEventHelper(){
            @Override
            public void onStartPage(PdfWriter writer, Document document) {
                PdfContentByte cb = writer.getDirectContent();
                cb.saveState();
                cb.beginText();
                BaseFont bf = null;
                try {
                    bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                cb.setFontAndSize(bf, 10);
                float y = document.bottom(-20);
                cb.showTextAligned(PdfContentByte.ALIGN_CENTER,  "-"+writer.getPageNumber()+"-", (document.right() + document.left()) / 2, y, 0);
                cb.endText();
                cb.restoreState();
            }
        });
        doc.open();
        float height = 0f;
        for (int i = 0; i < orginPdfList.size(); i++) {
            PdfReader reader = new PdfReader(orginPdfList.get(i));
            PdfContentByte cb = writer.getDirectContent();
            PdfImportedPage page = writer.getImportedPage(reader, 1);
            height = page.getHeight();
            if (i == 0) {
                //设置 比例 放大或者缩小 以及防止位置
                cb.addTemplate(page, 0.95, 0, 0, 0.95, 0, height);
            }
            if (i == 1) {
                cb.addTemplate(page, 0.95, 0, 0, 0.95, 300, height);
            }
            if (i == 2) {
                cb.addTemplate(page, 0.95, 0, 0, 0.95, 0, height - 410);
            }
            if (i == 3) {
                cb.addTemplate(page, 0.95, 0, 0, 0.95, 300, height - 410);
            }
        }
        doc.close();
    }




    /**
     * 对象转Map
     *
     * @param object
     * @return
     * @throws IllegalAccessException
     */
    public static Map beanToMap(Object object) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Field[] fields = object.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                map.put(field.getName(), field.get(object));
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return map;
    }
}
@Data
class PdfDataImgaModel {
    private String Image1 = null;//左上角图片
    private String Image2 = null; //专用章图片
    private String Image3 = null; //右下角图片
    private String Image5 = null; //检验者
    private String Image6 = null; //审核者
    private String Image7 = null;//批准人
    private String Image4 = null; //条形码图片
}
@Data
class PdfDataModel {
    private String Text1 = null; //条形码
    private String Text2 = null; //送检单位
    private String Text3 = null; //姓名
    private String Text4 = null; //性别
    private String Text14 = null;//年龄
    private String Text5 = null; //身份证号
    private String Text6 = null; //送检医师
    private String Text7 = null; //病 员 号
    private String Text8 = null; //科    别
    private String Text9 = null; //病人电话
    private String Text10 = null; //标本类型
    private String Text11 = null;//标本性状
    private String Text12 = null; //采样时间
    private String Text13 = null; //接收时间
    private String Text15 = null; //英文主题
    private String Text22 = null; //汉语主题
    private String Text23 = null; //建议与解释：
    private String Text16 = null; //备 注
    private String Text17 = null; //院方条形码
    private String Text18 = null; //网站地址:
    private String Text19 = null;//地址：
    private String Text20 = null; //客服电话
    private String Text21 = null; //报告日期
}
@Data
class TestItemsModel {
    //检测项目
    private String title;
    //结果
    private String result;
    //提示参考范围
    private String tag;

    //单位
    private String unit;



}
