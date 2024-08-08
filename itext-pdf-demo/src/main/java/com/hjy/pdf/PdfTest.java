package com.hjy.pdf;



import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import lombok.Builder;
import lombok.Data;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * @auther: hjy
 * @Date: 2024/3/19 17:26
 * @Description:
 */

public class PdfTest {

    public static void main(String[] args) throws Exception {

        Document document = new Document(PageSize.A4);
        PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream("./doc/test.pdf"));
        //
        pdfWriter.setPageEvent(new MyHeaderFooterPageEventHelper("左上标题", "右上标题", "左下标题", "测试水印"));

        document.open();

        document.addAuthor("作责");
        document.addCreationDate();
        document.addTitle("标题");
        document.addKeywords("关键字");
        document.addCreator("创建人");

        // Title
        document.add(createTitle("Java PDF生成"));

        // Chapter 1
        document.add(createChapter("1. 知识准备",22));
        document.add(createChapter("1.1 什么是TEXT",18));
        document.add(createParagraph(
                "Apache iText 是一个开源 Java 库，支持 PDF 文档的开发和转换。其目前遵从AGPL开源协议，AGPL 可以说是最严格的 GPL 了，并且Itext有很多product开始收费，但所需的功能基本上开源的API都能满足。"));
        document.add(createChapter("1.2 Apache iText中基础概念",18));
        document.add(createParagraph("基本上开源的API"));

        // Chapter 2
        document.add(createChapter("2. 实现案例",22));
        document.add(createChapter("2.1 用户列表示例",18));
        document.add(createParagraph("以导出用户列表为例"));

        // 表格
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            userList.add(User.builder().id(Long.parseLong(i + "")).userName("姓名" + i).email("邮箱" + i).phoneNumber(123456L)
                    .description("hello world" + i).build());
        }
        PdfPTable table = new PdfPTable(new float[] {20, 40, 50, 40, 40});
        table.setTotalWidth(500);
        table.setLockedWidth(true);
        table.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.getDefaultCell().setBorder(1);

        for (int i = 0; i < userList.size(); i++) {
            table.addCell(createCell(userList.get(i).getId() + ""));
            table.addCell(createCell(userList.get(i).getUserName()));
            table.addCell(createCell(userList.get(i).getEmail()));
            table.addCell(createCell(userList.get(i).getPhoneNumber() + ""));
            table.addCell(createCell(userList.get(i).getDescription()));
        }
        document.add(table);

        document.add(createChapter("2.2 图片导出示例",18));
        document.add(createParagraph("以导出图片为例"));
        // 图片
        Image image = Image.getInstance("./doc/aaa.jpg");
        image.setAlignment(Element.ALIGN_CENTER);
        image.scalePercent(60); // 缩放
        document.add(image);

        // close
        document.close();
    }


    /**
     * 标题
     * @param content
     * @return
     * @throws IOException
     * @throws DocumentException
     */
    public static Paragraph createTitle(String content) throws IOException, DocumentException {
        Font font = new Font(getBaseFont(), 24, Font.BOLD);
        Paragraph paragraph = new Paragraph(content, font);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        return paragraph;
    }


    /**
     * @param content
     * @return
     * @throws IOException
     * @throws DocumentException
     */
    public static Paragraph createChapter(String content,int fontSize) throws IOException, DocumentException {
        Font font = new Font(getBaseFont(), fontSize, Font.BOLD);
        Paragraph paragraph = new Paragraph(content, font);
        paragraph.setAlignment(Element.ALIGN_LEFT);
        return paragraph;
    }

    /**
     * 段落
     * @param content
     * @return
     * @throws IOException
     * @throws DocumentException
     */
    public static Paragraph createParagraph(String content) throws IOException, DocumentException {
        Font font = new Font(getBaseFont(), 12, Font.NORMAL);
        Paragraph paragraph = new Paragraph(content, font);
        paragraph.setAlignment(Element.ALIGN_LEFT);
        paragraph.setIndentationLeft(12); //设置左缩进
        paragraph.setIndentationRight(12); //设置右缩进
        paragraph.setFirstLineIndent(24); //设置首行缩进
        paragraph.setLeading(20f); //行间距
        paragraph.setSpacingBefore(5f); //设置段落上空白
        paragraph.setSpacingAfter(10f); //设置段落下空白
        return paragraph;
    }

    /**
     * 单元格
     * @param content
     * @return
     * @throws IOException
     * @throws DocumentException
     */
    public static PdfPCell createCell(String content) throws IOException, DocumentException {
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        Font font = new Font(getBaseFont(), 12, Font.NORMAL);
        cell.setPhrase(new Phrase(content, font));
        return cell;
    }

    /**
     * 字体
     * @return
     * @throws IOException
     * @throws DocumentException
     */
    public static BaseFont getBaseFont() throws IOException, DocumentException {
        return BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
    }

}

@Data
@Builder
class User {
    private Long id;
    private String userName;
    private String email;
    private Long phoneNumber;
    private String description;
}
class MyHeaderFooterPageEventHelper extends PdfPageEventHelper {

    private String headLeftTitle;

    private String headRightTitle;

    private String footerLeft;

    private String waterMark;

    // 模板
    public PdfTemplate total;

    public BaseFont bf = null;
    private Font basefont = null;

    {
        try {
            bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            basefont = new Font(bf, 12, Font.NORMAL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public MyHeaderFooterPageEventHelper(String headLeftTitle, String headRightTitle, String footerLeft, String waterMark) {
        this.headLeftTitle = headLeftTitle;
        this.headRightTitle = headRightTitle;
        this.footerLeft = footerLeft;
        this.waterMark = waterMark;
    }

    /**
     * 文档打开时创建模板
     */
    public void onOpenDocument(PdfWriter writer, Document document) {
        total = writer.getDirectContent().createTemplate(50, 50);// 共 页 的矩形的长宽高
    }

    // 一页加载完成触发，写入页眉和页脚
    @Override
    public void onEndPage(PdfWriter writer, Document document) {

        // page header and footer
        addPageHeaderAndFooter(writer, document, bf);

        // watermark
        if (waterMark != null) {
            addWaterMark(writer, document, bf);
        }
    }

    private void addPageHeaderAndFooter(PdfWriter writer, Document document, BaseFont bf) {

        PdfContentByte cb = writer.getDirectContent();
        cb.saveState();
        cb.beginText();
        cb.setColorFill(BaseColor.GRAY);
        cb.setFontAndSize(bf, 10);
        // header
        float x = document.top(-10);
        cb.showTextAligned(PdfContentByte.ALIGN_LEFT, headLeftTitle, document.left(), x, 0);
        cb.showTextAligned(PdfContentByte.ALIGN_RIGHT, headRightTitle, document.right(), x, 0);

        // footer
        float y = document.bottom(-10);
        cb.showTextAligned(PdfContentByte.ALIGN_LEFT, footerLeft, document.left(), y, 0);
        cb.showTextAligned(PdfContentByte.ALIGN_CENTER, String.format("- %d /", writer.getPageNumber()), (document.right() + document.left()) / 2, y, 0);
        cb.endText();
        cb.restoreState();

        cb.addTemplate(total, (document.rightMargin() + document.right() + document.leftMargin() - document.left()) / 2.0F + 10F, document.bottom() - 10);

    }

    private void addWaterMark(PdfWriter writer, Document document, BaseFont bf) {
        for (int i = 1; i < 7; i++) {
            for (int j = 1; j < 10; j++) {
                PdfContentByte cb = writer.getDirectContent();
                cb.saveState();
                cb.beginText();
                cb.setColorFill(BaseColor.GRAY);
                PdfGState gs = new PdfGState();
                gs.setFillOpacity(0.1f);
                cb.setGState(gs);
                cb.setFontAndSize(bf, 12);
                cb.showTextAligned(Element.ALIGN_MIDDLE, waterMark, 75 * i, 80 * j, 30);
                cb.endText();
                cb.restoreState();
            }
        }
    }

    // 全部完成后，将总页数的pdf模版写到指定位置
    @Override
    public void onCloseDocument(PdfWriter writer, Document document) {
        basefont.setColor(BaseColor.GRAY);
        basefont.setSize(10);
        Paragraph paragraph = new Paragraph((writer.getPageNumber()) + " -", basefont);
        ColumnText.showTextAligned(total, Element.ALIGN_LEFT, paragraph, 0, 0, 0);
    }



}
