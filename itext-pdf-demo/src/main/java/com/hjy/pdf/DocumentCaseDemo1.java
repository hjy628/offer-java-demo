package com.hjy.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import javax.swing.*;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @auther: hjy
 * @Date: 2024/3/19 16:03
 * @Description:
 */

public class DocumentCaseDemo1 {



    /**
     * 支持中文
     * @return
     */
    public static Font getChineseFont() {
        BaseFont bfChinese;
        Font fontChinese = null;
        try {
            //支持中文
            bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            fontChinese = new Font(bfChinese, 12, Font.NORMAL, BaseColor.BLUE);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fontChinese;

    }


    /**
     * .PdfWriter水印
     * @throws Exception
     */
    public static void testShuiyin() throws Exception {
        FileOutputStream out = new FileOutputStream("./doc/13.pdf");
        Document document = new Document(PageSize.A4);
        PdfWriter writer = PdfWriter.getInstance(document, out);
        JLabel label = new JLabel();
        int textH = 0;
        int textW = 0;
        int interval = -5;
        String waterMarkName="测试水印";   //需要添加的水印文字
        label.setText(waterMarkName);
        FontMetrics metrics = label.getFontMetrics(label.getFont());
        textH = metrics.getHeight();  //字符串的高,   只和字体有关
        textW = metrics.stringWidth(label.getText());  //字符串的宽
        float opacity=0.1f;//水印字体透明度
        int fontsize=12;  //水印字体大小
        int angle=30;   //水印倾斜角度（0-360）
        int heightRatio=2; //数值越大每页竖向水印越少
        int widthRatio=2;   //数值越大每页横向水印越少
        // 设置水印透明度
        PdfGState gs = new PdfGState();
        //这里是透明度设置
        gs.setFillOpacity(opacity);
        //这里是条纹不透明度
        gs.setStrokeOpacity(0.1f);
        document.open();
        // 在内容下方
        PdfContentByte under = writer.getDirectContentUnder();
        // 在内容上方
//         under = writer.getDirectContent();
        under.beginText();
        under.setGState(gs);
        BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.EMBEDDED);
        under.setFontAndSize(bf, fontsize);
        Rectangle rectangle = document.getPageSize();
//         under.setTextMatrix(30, 30);
        // 水印文字成45度角倾斜
        for (int height =  interval + textH; height < rectangle.getHeight()*2; height = height + textH * heightRatio) {
            for (int width =  interval + textW; width < rectangle.getWidth()*1.5 + textW; width = width + textW * widthRatio) {
                // rotation:倾斜角度
                under.showTextAligned(Element.ALIGN_LEFT, waterMarkName, width - textW, height - textH, angle);
            }
        }
        under.endText();
        document.add(new Paragraph("测试",getChineseFont()));
        document.close();
    }


    /**
     * PdfStamper加水印
     * @throws Exception
     */
    public static void testShuiyin1() throws Exception {
        FileOutputStream out = new FileOutputStream("./doc/14.pdf");
        // 读取器
        PdfReader reader = new PdfReader("./doc/12.pdf");
        // 解析器与输出
        PdfStamper stamp = new PdfStamper(reader, out);
        // 图片水印
        Image img = Image.getInstance("./doc/aaa.jpg");
        img.setAbsolutePosition(100, 100);// 位置
        // 在内容下方添加
        PdfContentByte under = stamp.getOverContent(1);// 拿到层,页数
        under.addImage(img);
        // 文字水印
        // 在内容上方添加
        PdfContentByte over = stamp.getOverContent(1);// 拿到层
        over.beginText();
        BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        over.setFontAndSize(bf, 18);
        over.setTextMatrix(30, 30);
        over.showTextAligned(Element.ALIGN_LEFT, "文字水印", 230, 430, 45);
        over.endText();
        // 背景图
        Image img2 = Image.getInstance("./doc/a.jpg");
        //水印与背景的区别：背景只需要把绝对置为从 文档左下角开始。即设置setAbsolutePosition(0, 0)
        img2.setAbsolutePosition(0, 0);
        PdfContentByte under2 = stamp.getUnderContent(1);
        under2.addImage(img2);
        // 关闭
        stamp.close();
        reader.close();
    }


    /**
     * 添加水印
     * @author ShaoMin
     * @throws IOException
     *
     */
    public static void testShuiyin2() throws Exception {
        FileOutputStream out = new FileOutputStream("./doc/15.pdf");
        Document doc = new Document();
        PdfWriter writer = PdfWriter.getInstance(doc, out);
        doc.open();
        writer.setPageEvent(new PdfPageHelper());
        doc.newPage();
        doc.add(new Chunk("aaa"));
        doc.newPage();
        doc.add(new Chunk("bbb"));
        doc.close();
    }


    /**
     * 第一种方式：现有的PDF追加页眉页码
     * @throws Exception
     */
    public static void insertHeadAndFoot2() throws Exception {
        FileOutputStream out = new FileOutputStream("./doc/17.pdf");
        Document doc = new Document(PageSize.A4);
        ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
        PdfWriter.getInstance(doc, outputStream);
        doc.open();
        doc.add(new Paragraph("1 page"));
        doc.newPage();
        doc.add(new Paragraph("2 page"));
        doc.close();


        BaseFont bf = null;
        try {
            bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //读取现存在的pdf文件 追加页码
        PdfReader reader = new PdfReader(outputStream.toByteArray());
        int numberOfPages = reader.getNumberOfPages();
        PdfStamper stamp = new PdfStamper(reader, out);
        for (int i = 1; i <= numberOfPages; i++) {
            PdfContentByte cb = stamp.getOverContent(i);// 页数
            cb.beginText();
            cb.setFontAndSize(bf, 10);
            cb.showTextAligned(PdfContentByte.ALIGN_CENTER, "- " + (i) + " -", (doc.right() + doc.left()) / 2, doc.bottom(-20), 0);
            cb.endText();

        }
        stamp.close();
        reader.close();
    }

    /**
     * 监听器 - PdfPageEvent. 如果无法确定总页数，可以该监听器重写onEndPage方法。
     *
     * writer.setPageEvent() 方法要放在 document.open() 方法之前，这样才能确定总数
     * @throws Exception
     */
    public static void insertHeadAndFoot() throws Exception {
        FileOutputStream out = new FileOutputStream("./doc/16.pdf");
        Document doc = new Document();
        PdfWriter writer = PdfWriter.getInstance(doc, out);
        // 内部类，处理器
        writer.setPageEvent(new PdfPageEventHelper() {
            // 模板
            public PdfTemplate total;
            private  BaseFont bf = null;
            private Font basefont = null;

            {
                try {
                    bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
                    basefont = new Font(bf, 12, Font.NORMAL);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            /**
             * 文档打开时创建模板
             */
            public void onOpenDocument(PdfWriter writer, Document document) {
                total = writer.getDirectContent().createTemplate(50, 50);// 共 页 的矩形的长宽高
            }
            @Override
            public void onEndPage(PdfWriter writer, Document document) {
                PdfContentByte cb = writer.getDirectContent();
                // 写入页眉
                ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase("页眉", basefont), document.left(), document.top() + 20, 0);
                //写入页码
                int pageS = writer.getPageNumber();
                String foot1 = "第 " + pageS + " 页 /共";
                float len = bf.getWidthPoint(foot1, 12);
                Phrase footer = new Phrase(foot1, basefont);
                ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, footer,(document.rightMargin() + document.right() + document.leftMargin() - document.left() - len) / 2.0F + 20F, document.bottom() - 20, 0);
                cb.addTemplate(total, (document.rightMargin() + document.right() + document.leftMargin() - document.left()) / 2.0F + 20F, document.bottom() - 20);
            }

            /**
             * 关闭文档时，替换模板，完成整个页眉页脚组件
             */
            public void onCloseDocument(PdfWriter writer, Document document) {
                // 关闭文档的时候，将模板替换成实际的 Y 值,至此，page x of y 制作完毕，完美兼容各种文档size。
                total.beginText();
                total.setFontAndSize(bf, 12);// 生成的模版的字体、颜色
                String foot2 = " " + (writer.getPageNumber()) + " 页";
                total.showText(foot2);// 模版显示的内容
                total.endText();
                total.closePath();
            }

        });
        doc.open();
        doc.add(new Paragraph("1 page"));
        doc.newPage();
        doc.add(new Paragraph("2 page"));
        doc.close();
    }


    /**
     * 读取PDF 内容
     * @return
     * @throws Exception
     */
    public static String testPdfContent() throws Exception{
        PDDocument document = PDDocument.load(new FileInputStream("doc/99.pdf"));
        document.getClass();
        //使用PDFTextStripper 工具
        PDFTextStripper tStripper = new PDFTextStripper();
        //设置文本排序，有规则输出
        tStripper.setSortByPosition(true);
        //获取所有文字信息
        String info = tStripper.getText(document);
        return  info;
    }


    /**
     * 删除pdf内容
     * @param srcUrl
     * @param outputPdfFile
     * @throws Exception
     */
    public static void deletePdfContent(String srcUrl, String outputPdfFile) throws Exception {
        PdfReader reader = new PdfReader(srcUrl);
        Document doc = new Document(reader.getPageSize(1));
        PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(outputPdfFile));
        doc.open();
        doc.newPage();
        PdfContentByte cb = writer.getDirectContent();
        PdfImportedPage page = writer.getImportedPage(reader, 1);
        cb.addTemplate(page, 0, 0);
        //??底的覆盖层
        cb.saveState();
        cb.setColorFill(BaseColor.WHITE);
        cb.rectangle(0f, 0f, doc.getPageSize().getWidth(), doc.bottom(85));
        cb.fill();
        cb.restoreState();
        doc.close();
    }

    public static void main(String[] args) throws Exception{

//        testShuiyin();
//        testShuiyin1();
//        testShuiyin2();
//        insertHeadAndFoot2();
//        insertHeadAndFoot();
        testPdfContent();
    }



}
