package com.hjy.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @auther: hjy
 * @Date: 2024/3/19 17:23
 * @Description:
 */

public class PdfExampleDemo {

    public static void main(String[] args) {
        Document document = new Document(PageSize.A4, 10, 10, 36, 36);
        try {
            PdfWriter.getInstance(document, new FileOutputStream("./doc/12.pdf"));
            document.open();
            BaseFont bfChines = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            Font FontChinese = new Font(bfChines, 18, Font.BOLD);
            Font FontData = new Font(bfChines, 12, Font.UNDEFINED);
            Font FontTable = new Font(bfChines, 10, Font.NORMAL);
            Font FontTableHeader = new Font(bfChines, 8, Font.BOLD);

            Paragraph titlle = new Paragraph("项目成本单", FontChinese);
            titlle.setAlignment(Element.ALIGN_CENTER);
            document.add(titlle);

            PdfPTable tableHeader = new PdfPTable(2);
            PdfPCell cell = new PdfPCell(new Paragraph("项目编号: " + "0333333333", FontData));

            cell.setBorder(Rectangle.NO_BORDER);
            tableHeader.addCell(cell);
            cell = new PdfPCell(new Paragraph("项目名称: " + "xxxxx开发项目", FontData));
            cell.setBorder(Rectangle.NO_BORDER);
            tableHeader.addCell(cell);

            cell = new PdfPCell(new Paragraph("项目例属: " + "xxxxx", FontData));
            cell.setBorder(Rectangle.NO_BORDER);
            tableHeader.addCell(cell);

            cell = new PdfPCell(new Paragraph("负责人: " + "xxxxx", FontData));
            cell.setBorder(Rectangle.NO_BORDER);
            tableHeader.addCell(cell);

            cell = new PdfPCell(new Paragraph("时间: " + new SimpleDateFormat("yyyy-MM-dd").format(new Date()), FontData));
            cell.setBorder(Rectangle.NO_BORDER);
            // 设置表上边的距离
            tableHeader.setSpacingBefore(30f);
            tableHeader.addCell(cell);

            float[] widths = {0.5f, 0.5f}; // 设置列的宽度 百分比

            tableHeader.setWidths(widths);
            tableHeader.setSpacingBefore(15f);
            document.add(tableHeader);

            PdfPTable table = new PdfPTable(7);
            cell = new PdfPCell(new Paragraph("编号", FontTableHeader));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 剧中显示
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("名称", FontTableHeader));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 剧中显示
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("数量", FontTableHeader));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 剧中显示
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("单位", FontTableHeader));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 剧中显示
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("单价（元）", FontTableHeader));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 剧中显示
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("折扣（%）", FontTableHeader));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 剧中显示
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("公式", FontTableHeader));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 剧中显示
            table.addCell(cell);

            // 固定高度
            cell.setFixedHeight(20f);

            for (int i = 0; i < 40; i++) {
                cell = new PdfPCell(new Paragraph((i + 1) + "", FontTable));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 水平居中
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 垂直居中
                table.addCell(cell);
                cell = new PdfPCell(new Paragraph("JAVA", FontTable));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 水平居中
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 垂直居中
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph("1", FontTable));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 水平居中
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 垂直居中
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph("部", FontTable));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 水平居中
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 垂直居中
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph("5000", FontTable));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 水平居中
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 垂直居中
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph("80%", FontTable));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 水平居中
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 垂直居中
                table.addCell(cell);
                cell = new PdfPCell(new Paragraph("xxxx", FontTable));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 水平居中
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 垂直居中
                table.addCell(cell);
                // 每页设置表头
                table.setHeaderRows(1);
            }

            float[] width = {0.2f, 0.2f, 0.1f, 0.1f, 0.1f, 0.1f, 0.2f};
            table.setWidths(width);
            table.setSpacingBefore(15f);
            document.add(table);

            PdfPTable tableBottom = new PdfPTable(2);
            cell = new PdfPCell(new Paragraph("负责人：xxxx", FontData));
            cell.setBorder(Rectangle.NO_BORDER);
            tableBottom.addCell(cell);

            cell = new PdfPCell(new Paragraph("编号：xxxx", FontData));
            cell.setBorder(Rectangle.NO_BORDER);
            tableBottom.addCell(cell);

            cell = new PdfPCell(new Paragraph("最近修改人：xxxx", FontData));
            cell.setBorder(Rectangle.NO_BORDER);
            tableBottom.addCell(cell);

            cell = new PdfPCell(new Paragraph("业务归属人：xxxx", FontData));
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(2);
            tableBottom.addCell(cell);

            cell = new PdfPCell(new Paragraph("备注：开发进度", FontData));
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(2);
            tableBottom.addCell(cell);

            tableBottom.setSpacingBefore(15f);
            document.add(tableBottom);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
