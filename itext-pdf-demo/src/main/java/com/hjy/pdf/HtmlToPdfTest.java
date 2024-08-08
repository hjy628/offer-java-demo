package com.hjy.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.codec.binary.Base64;
import java.io.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * @auther: hjy
 * @Date: 2024/3/20 13:59
 * @Description:
 * html 模板 生成pdf
 */

public class HtmlToPdfTest {

    /**
     * html渲染为pdf
     *
     * @param data 变量
     * @param htmlTmp 模板文件名
     * @param pdftemp pdf导出路径
     * @return
     */
    public static void freeMarkerRender(Map<String, Object> data, String htmlTmp, String pdftemp) throws Exception {
        // 获取模板,并设置编码方式
        Configuration  freemarkerCfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        //文件夹目录位置
        freemarkerCfg.setDirectoryForTemplateLoading(new File("./doc"));
        Template template = freemarkerCfg.getTemplate(htmlTmp,"UTF-8");
        StringWriter out = new StringWriter();
        // 合并模板跟数据
        template.process(data, out);
        // htmlData 模板字符流
        String htmlData = out.toString();
        // 设置文档格式,数字边距
        Document document = new Document(PageSize.A4);
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pdftemp));
        // 添加页码
        writer.setPageEvent(new PdfReportM1HeaderFooter());
        // 打开文档
        document.open();

        // HTML 转成pdf
        XMLWorkerHelper.getInstance().parseXHtml(writer, document, new ByteArrayInputStream(htmlData.getBytes()), Charset.forName("UTF-8"),
                new MyFontsProvider(12));
        // 关闭文档
        document.close();
    }

    /**
     * @param file
     * @return
     */
    public static String fileToBase64Str(File file) {
        byte[] data = null;
        InputStream inputStream = null;
        if (file != null) {
            try {
                inputStream = new FileInputStream(file);
                data = new byte[inputStream.available()];
                inputStream.read(data);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return Base64.encodeBase64String(data);
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        Map<String, Object> data = new HashMap();
        data.put("name",123);
        data.put("imgUrl","https://ccdn.goodq.top/caches/4a7b7daf436f3e5cb2e29433375ccd5d/aHR0cHM6Ly81NTlhNDQ3YzczMmVlLnQ3My5xaWZlaXllLmNvbS9xZnktY29udGVudC91cGxvYWRzLzIwMTQvMDYvYjU5YzY3YmYxOTZhNDc1ODE5MWU0MmY3NjY3MGNlYmEuanBn.jpg");
        freeMarkerRender(data, "freemarker.html", "./doc/ad.pdf");
    }

}

class MyFontsProvider extends XMLWorkerFontProvider {

    private int fontSize;

    public MyFontsProvider() {
        this.fontSize = 0;
    }

    public MyFontsProvider(int fontSize) {
        this.fontSize = fontSize;
    }

    @Override
    public Font getFont(final String fontname, final String encoding, final boolean embedded, final float size, final int style, final BaseColor color) {
        BaseFont bf = null;
        try {
            bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Font font = null;
        if (fontSize != 0) {
            font = new Font(bf, fontSize, style, color);
        } else {
            font = new Font(bf, size, style, color);
        }
        font.setColor(color);
        return font;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

}
class PdfReportM1HeaderFooter extends PdfPageEventHelper {

    /**
     * 页眉
     */
    public String header = "页眉";

    /**
     * 文档字体大小，页脚页眉最好和文本大小一致
     */
    public int presentFontSize = 12;

    // 模板
    public PdfTemplate total;

    private  BaseFont bf = null;

    public PdfReportM1HeaderFooter() {

    }

    /**
     * 文档打开时创建模板
     */
    public void onOpenDocument(PdfWriter writer, Document document) {
        total = writer.getDirectContent().createTemplate(50, 50);// 共 页 的矩形的长宽高
    }

    /**
     * 关闭每页的时候，写入页眉，写入'第几页共'这几个字。
     *
     * @see com.itextpdf.text.pdf.PdfPageEventHelper#onEndPage(com.itextpdf.text.pdf.PdfWriter, com.itextpdf.text.Document)
     */
    public void onEndPage(PdfWriter writer, Document document) {
        Font fontDetail = null;
        try {
            bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", false);
            fontDetail = new Font(bf, presentFontSize, Font.NORMAL);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 1.写入页眉
        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT, new Phrase(header, fontDetail), document.left(), document.top() + 20, 0);
        // 2.写入前半部分的 第 X页/共
        int pageS = writer.getPageNumber();
        String foot1 = "第 " + pageS + " 页 /共";
        Phrase footer = new Phrase(foot1, fontDetail);
        // 3.计算前半部分的foot1的长度，后面好定位最后一部分的'Y页'这俩字的x轴坐标，字体长度也要计算进去 = len
        float len = bf.getWidthPoint(foot1, presentFontSize);
        // 4.拿到当前的PdfContentByte
        PdfContentByte cb = writer.getDirectContent();
        // 5.写入页脚
        ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, footer,(document.rightMargin() + document.right() + document.leftMargin() - document.left() - len) / 2.0F + 20F, document.bottom() - 20, 0);

        cb.addTemplate(total, (document.rightMargin() + document.right() + document.leftMargin() - document.left()) / 2.0F + 20F, document.bottom() - 20);

    }

    /**
     * 关闭文档时，替换模板，完成整个页眉页脚组件
     */
    public void onCloseDocument(PdfWriter writer, Document document) {
        // 关闭文档的时候，将模板替换成实际的 Y 值,至此，page x of y 制作完毕，完美兼容各种文档size。
        total.beginText();
        total.setFontAndSize(bf, presentFontSize);// 生成的模版的字体、颜色
        String foot2 = " " + (writer.getPageNumber()) + " 页";
        total.showText(foot2);// 模版显示的内容
        total.endText();
        total.closePath();
    }




}
