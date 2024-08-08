package com.hjy.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @auther: hjy
 * @Date: 2024/3/18 17:26
 * @Description:
 *
 * Document document =new Document(); // 默认页面大小是A4
 * Document document =new Document(PageSize.A4); // 指定页面大小为A4
 * Document document =new Document(PageSize.A4,50,50,30,20); // 指定页面大小为A4，且自定义页边距(marginLeft、marginRight、marginTop、marginBottom)
 * 其中页面大小PageSize也可自定义大小，例：new Document(new Rectangle(400, 500));
 *
 * // 作者
 * document.addAuthor("作者");
 * // 创建日期
 * document.addCreationDate();
 * // 创建关键字
 * document.addKeywords("测试");
 * // 创建生产商，自动使用iText
 * document.addProducer();
 * // 创建程序
 * document.addCreator("创建人");
 * // 标题
 * document.addTitle("标题");
 * // 主题
 * document.addSubject("主题");
 *
 * //页边空白
 * document.setMargins(10, 20, 30, 40);
 *
 *
 *
 * //PDF添加内容
 * document.add(new Paragraph("PDF添加内容"));
 * //添加Page
 * document.newPage();
 * //控制是否显示空白页
 * writer.setPageEmpty(true);
 * //监听器
 * document.addDocListener();
 * //添加js脚本
 * document.setJavaScript_onLoad();
 *
 * //横向打印
 *
 * document = new Document(PageSize.A4.rotate());
 *
 * document = new Document(tRectangle.rotate());
 */

public class DocumentDemo2 {



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
     * 表格操作 PdfPTable、PdfPCell
     * @throws Exception
     *
     * 构造方法：
     *
     * //列数
     * PdfPTable datatable = new PdfPTable(6);
     * //每个单元格宽度
     * PdfPTable datatable = new PdfPTable(new float[]{1,2,3})
     * //表格嵌套
     * PdfPTable rootTable=new PdfPTable(new PdfPTable(6));
     *
     * PdfPCell cell= new PdfPCell(new Paragraph(“表格”, 中文支持）
     * //默认单元格
     * PdfPCell cell1=new PdfPCell();
     * //单元格放入table
     * PdfPCell cell2=new PdfPCell(table,new PdfPCell())
     * //单元格放入图片
     * PdfPCell cell3=new PdfPCell(Image image);
     *
     *
     * 2、结构：
     *
     * PdfPTable[PdfPTable[PdfPCell[Paragraph]]]
     * PdfPTable[PdfPTable[PdfPCell[PdfPTable,PdfPCell]]]
     *
     *
     * 3、方法：
     *
     * PdfPTable：
     *
     *  //设置表格上面空白行
     * setSpacingBefore(15f);
     * //设置表格下面空白行
     * setSpacingAfter(40f);
     * //第一行作为标题. 定义为标题的行应该保留在新页面上.
     * setHeaderRows(1);
     * // 设置列的宽度 百分比
     * setWidths(cellsWidth);
     * //表格的总宽度
     * setTotalWidth(300f);
     * // 表格的宽度百分比
     * setWidthPercentage(100);
     * //得到默认单元格
     * getDefaultCell()
     * 添加单元格
     * addCell()
     *
     * //写入绝对位置
     * pdfPTable.writeSelectedRows(0,-1, 0, -1, 100, 200, tContent);
     *
     * PdfPCell：
     * //背景色
     * setBackgroundColor(BaseColor.CYAN)
     * //最小高度
     * 2）setMinimumHeight(30f)
     * //固定高度。表格的高度通过单元格高度完成
     * setFixedHeight(40f)
     * //无边框
     * setBorder(Rectangle.NO_BORDER)
     * //无边框。不设，默认是有边框的
     * setBorderWidth(0)
     * setBorderWidthBottom(Rectangle.NO_BORDER);
     * setBorderWidthRight(Rectangle.NO_BORDER);
     * setBorderWidthTop(Rectangle.NO_BORDER);
     * setBorderWidthLeft(Rectangle.NO_BORDER);
     * //边框颜色
     * setBorderColor(new BaseColor(255, 0, 0))
     * //水平居中
     * setHorizontalAlignment(Element.ALIGN_CENTER)
     * //垂直居中。设置单元格内容的显示
     * setVerticalAlignment(Element.ALIGN_MIDDLE)
     * //跨2行
     * setRowspan(2)
     * //跨2列
     * setColspan(2)
     *
     *
     */
    public static void insertTableAndPdfPCell() throws Exception {

        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        // 使用PDFWriter进行写文件操作
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("./doc/13.pdf"));
        document.open();

        // 中文字体
        Font fontChinese = getChineseFont();

        Paragraph titlle=new Paragraph("测试表格",fontChinese);
        //中间
        titlle.setAlignment(Element.ALIGN_CENTER);
        document.add(titlle);


        PdfPTable pTable=new PdfPTable(2);


        PdfPCell pdfPCell=new PdfPCell(new Paragraph("编号:"+"111111111",fontChinese));
        pdfPCell.setBorder(Rectangle.NO_BORDER);
        pTable.addCell(pdfPCell);

        pdfPCell=new PdfPCell(new Paragraph("名称"+"aaaa",fontChinese));
        pdfPCell.setBorder(Rectangle.NO_BORDER);
        pTable.addCell(pdfPCell);

        float[] widths={1f,1f}; //设置列的宽度 百分比

        pTable.setWidths(widths);
        pTable.setSpacingBefore(15f);
        document.add(pTable);




        //设置表头
        String[] tableHeader=new String[6];
        String[] tableCont=new String[6];

        int colNumber = 6;
        for (int i = 0; i < 6; i++) {
            tableHeader[i]="表头"+(i+1);
            tableCont[i]="内容"+(i+1);
        }
        // 创建有6列的表格 可以设置重复表头
        PdfPTable datatable = new PdfPTable(colNumber);
        //设置表格上面空白行
        datatable.setSpacingBefore(15f);
        //每个单元格宽度
//        PdfPTable datatable = new PdfPTable(new float[]{1, 1, 1, 1, 1, 1})
        //第一行作为标题. 定义为标题的行应该保留在新页面上.
        datatable.setHeaderRows(1);
        // 定义表格的宽度
        int[] cellsWidth = {1, 1, 1, 1, 1, 1};
        // 设置列的宽度 百分比
        datatable.setWidths(cellsWidth);
        //表格的总宽度
        // datatable.setTotalWidth(300f);
        // 表格的宽度百分比
        datatable.setWidthPercentage(100);
        // 单元格的间隔
        datatable.getDefaultCell().setPadding(2);
        // 边框宽度
        datatable.getDefaultCell().setBorderWidth(2);
        // 设置表格的底色
        datatable.getDefaultCell().setBackgroundColor(BaseColor.GREEN);
        datatable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

        // 添加表头元素
        for (int i = 0; i < colNumber; i++) {
            datatable.addCell(new Paragraph(tableHeader[i], fontChinese));
        }

        // 添加表格的内容
        for (int i = 0; i < colNumber; i++) {
            datatable.addCell(new Paragraph(tableCont[i], fontChinese));
        }

        // 空白表格
        for (int i = 0; i < colNumber; i++) {
            PdfPCell cell = new PdfPCell(new Paragraph(""));
            // 单元格高度
            cell.setFixedHeight(20);
            datatable.addCell(cell);
        }
        // 设置表格下面空白行
        datatable.setSpacingAfter(40f);
        // 把表格加入文档
        document.add(datatable);

        // 跨行跨列表格
        PdfPTable table = new PdfPTable(3);
        // 3列表格
        PdfPCell cell; // 单元格
        cell = new PdfPCell(new Phrase("跨三列", getChineseFont()));
        // 跨3列
        cell.setColspan(3);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("跨二行", getChineseFont()));
        // 跨2行
        cell.setRowspan(2);
        table.addCell(cell);
        table.addCell(new PdfPCell(new Phrase("第一行,第一列", getChineseFont())));
        table.addCell(new PdfPCell(new Phrase("第一行,第二列", getChineseFont())));
        table.addCell(new PdfPCell(new Phrase("第二行,第一列", getChineseFont())));
        table.addCell(new PdfPCell(new Phrase("第二行,第二列", getChineseFont())));

        document.add(table);

        // 表格的嵌套 4列
        PdfPTable tableFather = new PdfPTable(4);
        //设置表格上面空白行
        tableFather.setSpacingBefore(20f);
        // 1行2列
        PdfPTable nested1 = new PdfPTable(2);
        //设置无边框
        nested1.getDefaultCell().setBorderWidthBottom(Rectangle.NO_BORDER);
        nested1.getDefaultCell().setBorderWidthRight(Rectangle.NO_BORDER);
        nested1.getDefaultCell().setBorderWidthTop(Rectangle.NO_BORDER);
        nested1.getDefaultCell().setBorderWidthLeft(Rectangle.NO_BORDER);
        nested1.addCell("1.1");
        nested1.getDefaultCell().setBorderWidthLeft(1);
        nested1.addCell("1.2");

        // 2行1列
        PdfPTable nested2 = new PdfPTable(1);
        nested2.getDefaultCell().setBorderWidthBottom(Rectangle.NO_BORDER);
        nested2.getDefaultCell().setBorderWidthRight(Rectangle.NO_BORDER);
        nested2.getDefaultCell().setBorderWidthTop(Rectangle.NO_BORDER);
        nested2.getDefaultCell().setBorderWidthLeft(Rectangle.NO_BORDER);
        nested2.addCell("2.1");
        nested2.getDefaultCell().setBorderWidthTop(1);
        nested2.addCell("2.2");

        // 将表格插入到指定位置
        for (int i = 0; i < 12; i++) {
            switch (i){
                case 1:
                    tableFather.addCell(nested1);
                    break;
                case 6:
                    tableFather.addCell(nested2);
                    break;
                default:
                    tableFather.addCell("cell " + i);
                    break;
            }

        }

        // 设置表格下面空白行
        tableFather.setSpacingAfter(40f);
        // 把表格加入文档
        document.add(tableFather);


        //表格嵌套
        PdfPTable rootTable=new PdfPTable(tableFather);
        document.add(rootTable);


        PdfPTable pdfPTable=new PdfPTable(1);
        pdfPTable.setTotalWidth(300f);
        //得到层
        PdfContentByte tContent = writer.getDirectContent();


        PdfPCell pdfPCell1=new PdfPCell(new Paragraph("编号:"+"fsddd",fontChinese));
        pdfPTable.addCell(pdfPCell1);

        //写入绝对位置
        pdfPTable.writeSelectedRows(0,-1, 0, -1, 100, 200, tContent);
        pTable.setSpacingBefore(15f);
        document.add(pdfPTable);

        document.close();
    }



    public static void main(String[] args) throws Exception{

        insertTableAndPdfPCell();
    }


}
