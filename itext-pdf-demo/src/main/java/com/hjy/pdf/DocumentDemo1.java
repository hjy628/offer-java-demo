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

public class DocumentDemo1 {

    /**
     * 创建pdf
     */
    public static void createPdf()throws Exception{
            // 1-创建文本对象 Document
            Document document = new Document(PageSize.A4, 500, 150, 50, 50);
            FileOutputStream out=new FileOutputStream("./doc/1.pdf");
            // 2-初始化 pdf输出对象 PdfWriter
            PdfWriter.getInstance(document, out);
            // 3-打开 Document
            document.open();
            // 4-往 Document 添加内容
            document.add(new Paragraph("test！ PDF！！！"));
            // 5-关闭 Document
            document.close();
    }


    /**
     * PdfWriter-文档解析器
     *
     * PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("./doc/11.pdf"));
     * //面框大小。允许的名称有：“crop”、“trim”、“art”和“bleach”。
     * writer.setBoxSize("crop",PageSize.A6);
     * writer.setPageEmpty(true);
     * //设置裁剪框大小
     * writer.setCropBoxSize(PageSize.A8);
     * // 设置密码为："123" 需要根绝itext版本添加加密算法依赖：http://mvnrepository.com/artifact/com.itextpdf/itextpdf/
     * writer.setEncryption("密码".getBytes(), "123".getBytes(),PdfWriter.ALLOW_SCREENREADERS,PdfWriter.STANDARD_ENCRYPTION_128);
     * //PDF版本
     * writer.setPdfVersion(PdfWriter.PDF_VERSION_1_2);
     */

    /**
     * Rectangle— 页面对象
     *
     * 构造方法：
     *
     * Rectangle(final float llx, final float lly, final float urx, final float ury)
     * Rectangle(PageSize.A4)
     * Rectangle(float urx, float ury)
     * //rotation旋转比例
     * Rectangle(float urx, float ury, int rotation)
     * Rectangle(Rectangle rect)
     *
     * 四个参数:
     *
     *         前面两个参数代表第一个点的 xy 坐标，后面两个参数代表第二个点的 xy 坐标值，Itext 将以这两个点作为对角点来创建一个矩形。
     *
     * 方法：
     *
     *  //旋转比例
     * tRectangle.setRotation();
     * //背景
     * tRectangle.setBackgroundColor();
     * //边框
     * tRectangle.setBorder();
     * //边框背景
     * tRectangle.setBorderColor();
     * //边框宽度
     * tRectangle.setBorderWidth();
     * tRectangle.setTop();
     * tRectangle.setLeft();
     * tRectangle.setRight();
     * tRectangle.setBottom();
     */


    /**
     *   Rectangle— 页面对象api
     * @throws Exception
     */
    public static void getRectangle() throws Exception {
        // 1-创建一个pdf文档,document
        Document document = new Document();// 默认PageSize.A4, 36, 36, 36, 36
        // 2-Rectangle（pdf页面）创建Document
        // 一般是四个参数表示：左下角的坐标和右上角的坐标
        Rectangle tRectangle = PageSize.A4;// PageSize封装了大量常用的Rectangle数据
        tRectangle = new Rectangle(800, 600);// 长宽
        tRectangle = new Rectangle(0, 0, 800, 600);// 等于上面

        //其他页面属性：不能和PageSize封装的静态一起使用
        tRectangle.setBackgroundColor(BaseColor.BLACK);// 背景色
        tRectangle.setBorder(1220);// 边框
        tRectangle.setBorderColor(BaseColor.BLUE);
        tRectangle.setBorderWidth(244.2f);
        document = new Document(tRectangle);
        // 解析器
        PdfWriter.getInstance(document, new FileOutputStream("./doc/3.pdf"));
        document.open();
        document.newPage();
        document.add(new Paragraph("New page"));
        document.close();

    }


    /**
     * 块对象测试
     * @throws Exception
     */
    public static void testChunk() throws Exception {
        // 1-创建一个pdf文档,document
        Document document = new Document();// 默认PageSize.A4, 36, 36, 36, 36
        // 解析器
        PdfWriter.getInstance(document, new FileOutputStream("./doc/4.pdf"));
        document.open();
        document.newPage();
        String[] contries = new String[] {"美国", "英甲", "中国", "朝鲜", "日本"};
        for (int index = 1; index <= contries.length; index++) {
            String contry = contries[index - 1];
            Chunk chunk = new Chunk(contry, getChineseFont());
            //字间隔
//            chunk.setWordSpacing(10f);
            //行间距
//            chunk.setLineHeight(20f);
            document.add(chunk);
            //块之间设置间隔
            document.add(new Chunk(" "));
            Font font = FontFactory.getFont("STSong-Light", "UniGB-UCS2-H", 6, Font.BOLD, BaseColor.WHITE);
            Chunk id = new Chunk(index + "", font);
            // 设置块的背景色
            id.setBackground(BaseColor.BLACK, 1f, 0.5f, 1f, 1.5f);
            // 设置上标，其中参数表示，离开基线的距离，如果设置负数就表示设置下标
            id.setTextRise(6);
            document.add(id);
            //块之间设置间隔
            document.add(new Chunk("  "));
            // 换行 需要设置行间距 不然上移 覆盖
//            document.add(Chunk.NEWLINE);
        }
        document.close();
    }


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
     *  Phrase 短语对象
     * @throws Exception
     */
    public static void testPhrase() throws Exception {
        // 1-创建一个pdf文档,document
        Document document = new Document();// 默认PageSize.A4, 36, 36, 36, 36
        // 解析器
        PdfWriter.getInstance(document, new FileOutputStream("./doc/5.pdf"));
        document.open();
        document.newPage();
        String[] contries = new String[] {"美国", "英甲", "中国", "朝鲜", "日本"};
        Font BOLD_UNDERLINED = FontFactory.getFont("STSong-Light", "UniGB-UCS2-H", 12, Font.BOLD | Font.UNDERLINE);
        Font NORMAL = FontFactory.getFont("STSong-Light", "UniGB-UCS2-H", 12);
        for (int index = 1; index <= contries.length; index++) {
            String contry = contries[index - 1];
            Phrase director = new Phrase();
            director.add(new Chunk(contry, BOLD_UNDERLINED));
            director.add(new Chunk(",", BOLD_UNDERLINED));
            director.add(new Chunk(" ", NORMAL));
            director.add(new Chunk(contry, NORMAL));
            //设置行间距
            director.setLeading(66f);
            document.add(director);
            //内部换行
            document.add(Chunk.NEWLINE);
        }
        document.close();
    }

    /**
     * Paragraph段落
     * @throws Exception
     * add(Element)-添加；
     *
     * setLeading(20f)-行间距，一个Paragraph只有一个行间距；
     * setIndentationLeft()-左缩进，
     *
     * setIndentationRight-右缩进，
     *
     * setFirstLineIndent-首行缩进；
     * setSpacingBefore-设置上空白，
     *
     * setSpacingAfter(10f)-设置段落下空；
     * setAlignment(Element.ALIGN_CENTER)-居中对齐；
     *
     * //直线
     * Paragraph p1 =new Paragraph();
     * p1.add(new Chunk(new LineSeparator()));
     * doc.add(p1);
     *
     * //点线
     * Paragraph p2 =new Paragraph();
     * p2.add(new Chunk(new DottedLineSeparator()));
     *
     * //下滑线
     * LineSeparator UNDERLINE = new LineSeparator(1, 100, null, Element.ALIGN_CENTER, -2);
     * Paragraph p3 = new Paragraph("NNNNNNNNNNN");
     * p3.add(UNDERLINE);
     * document.add(p3);
     */
    public static void testParagraph() throws Exception {
        // 1-创建一个pdf文档,document
        Document document = new Document();// 默认PageSize.A4, 36, 36, 36, 36
        // 解析器
        PdfWriter.getInstance(document, new FileOutputStream("./doc/6.pdf"));
        document.open();
        document.newPage();
        String[] contries = new String[] {"美国", "英甲", "中国", "朝鲜", "日本"};
        Font BOLD_UNDERLINED = FontFactory.getFont("STSong-Light", "UniGB-UCS2-H", 12, Font.BOLD | Font.UNDERLINE);
        Font NORMAL = FontFactory.getFont("STSong-Light", "UniGB-UCS2-H", 12);
        for (int index = 1; index <= contries.length; index++) {
            String contry = contries[index - 1];
            Paragraph p = new Paragraph();
            p.add(new Chunk("年代: ", BOLD_UNDERLINED));
            p.add(new Phrase("标题: ", NORMAL));
            p.add(new Phrase(contry, NORMAL));
            // 设置行间距
            p.setLeading(20f);
            document.add(p);
            // 内部换行
            document.add(Chunk.NEWLINE);
        }
        Paragraph paragraph = new Paragraph("这是一个缩进演示：段落是一系列块和（或）短句。同短句一样，段落有确定的间距。用户还可以指定缩排；"
                + "在边和（或）右边保留一定空白，段落可以左对齐、右对齐和居中对齐。添加到文档中的每一个段落将自动另起一行。说明：一个段落有一个且仅有一个间距，"
                + "如果你添加了一个不同字体的短句或块，原来的间距仍然有效，你可以通过SetLeading来改变间距，但是段落中所有内容将使用新的中的间距。更改分割符 通常，" +
                "当文本不能放在一行时，文本将被分割成不同的部分，iText首先会查找分割符，如果没有找到，文本将在行尾被截断。有一些预定的分割符如“ ”空格和“-”连字符，" +
                "但是你可以使用setSplitCharacter方法来覆盖这些默认值。",
                NORMAL);
        // 默认情况下，文本的对齐方式为左对齐
        // paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
        // 首行缩进（FirstLineIndent），左边缩进（indentationLeft），右边缩进（IndentationRight）
        paragraph.setFirstLineIndent(10f);
        paragraph.setIndentationLeft(10f);
        paragraph.setIndentationLeft(12f);
        document.add(paragraph);
        document.close();
    }

    /**
     * 列表(List)
     * @throws Exception
     */
    public static void testList() throws Exception {
        // 1-创建一个pdf文档,document
        Document document = new Document();// 默认PageSize.A4, 36, 36, 36, 36
        // 解析器
        PdfWriter.getInstance(document, new FileOutputStream("./doc/7.pdf"));
        document.open();
        document.newPage();
        String[] contries = new String[] {"美国", "英甲", "中国", "朝鲜", "日本"};
        Font NORMAL = FontFactory.getFont("STSong-Light", "UniGB-UCS2-H", 12);

        document.add(new Chunk("默认列表演示1：", NORMAL));
        document.add(Chunk.NEWLINE);

        List list = new com.itextpdf.text.List();
        for (int index = 1; index <= contries.length; index++) {
            String contry = contries[index - 1];
            list.add(new ListItem(contry, NORMAL));
        }
        document.add(list);
        document.add(Chunk.NEWLINE);

        document.add(new Chunk("不显示数字演示2：", NORMAL));
        document.add(Chunk.NEWLINE);

        // 编号
        list = new List(false);
        for (int index = 1; index <= contries.length; index++) {
            String contry = contries[index - 1];
            list.add(new ListItem(contry, NORMAL));
        }
        document.add(list);
        document.add(Chunk.NEWLINE);

        document.add(new Chunk("使用#作为列表符号3：", NORMAL));
        document.add(Chunk.NEWLINE);

        list = new List();
        // 设置编号
        list.setListSymbol("#");
        for (int index = 1; index <= contries.length; index++) {
            String contry = contries[index - 1];
            list.add(new ListItem(contry, NORMAL));
        }
        document.add(list);
        // 换行
        document.add(Chunk.NEWLINE);

        document.add(new Chunk("显示数字演示4：", NORMAL));
        document.add(Chunk.NEWLINE);
        list = new List(true);
        for (int index = 1; index <= contries.length; index++) {
            String contry = contries[index - 1];
            list.add(new ListItem(contry, NORMAL));
        }
        document.add(list);
        document.add(Chunk.NEWLINE);
        document.add(new Chunk("罗马数字列表演示5：", NORMAL));
        document.add(Chunk.NEWLINE);

        List list1 = new RomanList();
        for (int index = 1; index <= contries.length; index++) {
            String contry = contries[index - 1];
            list1.add(new ListItem(contry, NORMAL));
        }
        document.add(list1);

        document.add(Chunk.NEWLINE);
        document.add(new Chunk("希腊字母列表演示6：", NORMAL));
        document.add(Chunk.NEWLINE);

        List list2 = new GreekList();
        for (int index = 1; index <= contries.length; index++) {
            String contry = contries[index - 1];
            list2.add(new ListItem(contry, NORMAL));
        }
        document.add(list2);
        document.add(Chunk.NEWLINE);


        document.add(new Chunk("ZapfDingbatsNumberList演示7：", NORMAL));
        document.add(Chunk.NEWLINE);

        List list3 = new ZapfDingbatsNumberList(10);
        for (int index = 1; index <= contries.length; index++) {
            String contry = contries[index - 1];
            list3.add(new ListItem(contry, NORMAL));
        }
        document.add(list3);

        document.add(Chunk.NEWLINE);
        document.add(new Chunk("ZapfDingbatsList演示8：", NORMAL));
        document.add(Chunk.NEWLINE);

        List list4 = new ZapfDingbatsList(43, 30);
        for (int index = 1; index <= contries.length; index++) {
            String contry = contries[index - 1];
            list4.add(new ListItem(contry, NORMAL));
        }
        document.add(list4);

        document.add(Chunk.NEWLINE);
        document.add(new Chunk("列表嵌套演示9：", NORMAL));
        document.add(Chunk.NEWLINE);

        List rootList = new List(List.UNORDERED);
        rootList.add(new ListItem("Item 1"));
        // 子列表
        List sublist = new List(true, false, 30);
        sublist.setListSymbol(new Chunk("", FontFactory.getFont(FontFactory.HELVETICA, 6)));
        sublist.add("A");
        sublist.add("B");
        rootList.add(sublist);
        rootList.add(new ListItem("Item 2"));
        // 子列表
        sublist = new List(true, false, 30);
        sublist.setListSymbol(new Chunk("", FontFactory.getFont(FontFactory.HELVETICA, 6)));
        sublist.add("C");
        sublist.add("D");
        rootList.add(sublist);
        document.add(rootList);

        document.close();
    }

    /**
     * 图像
     * @throws Exception
     */
    public static void testImage() throws Exception {
        // 1-创建一个pdf文档,document
        Document document = new Document();// 默认PageSize.A4, 36, 36, 36, 36
        // 解析器
        PdfWriter.getInstance(document, new FileOutputStream("./doc/9.pdf"));
        document.open();
        document.newPage();
        // 图片Image对象
        Image img = Image.getInstance("./doc/n7.jpg");
        img.setAlignment(Image.LEFT);
        img.setBorder(Image.BOX);
        img.setBorderWidth(10);
        img.setBorderColor(BaseColor.WHITE);
        img.scaleToFit(800, 72);// 大小
        img.setRotation(-20);//旋转 弧度
        img.setRotationDegrees(30);// 旋转 角度
        img.scalePercent(10);//依照比例缩放
        document.add(img);
        document.close();
    }

    /**
     * Anchor（锚点、超链接）
     * @throws Exception
     */
    public static void testAnchor() throws Exception {
        // 1-创建一个pdf文档,document
        Document document = new Document();// 默认PageSize.A4, 36, 36, 36, 36
        // 解析器
        PdfWriter.getInstance(document, new FileOutputStream("./doc/10.pdf"));
        document.open();
        document.newPage();
        // Anchor超链接和锚点对象: internal and external links
        Paragraph country = new Paragraph();
        Anchor dest = new Anchor("我是锚点，也是超链接", getChineseFont());
        dest.setName("微车官网"); // 设置锚点的名字
        dest.setReference("http://uu.ttsales.cn");// 连接
        country.add(dest);
        country.add(String.format(": %d sites", 10000));
        document.add(country);
        document.close();
    }

    /**
     * Chapter、Section（大纲）
     * @throws Exception
     */
    public static void testChapterAndSection() throws Exception {
        // 1-创建一个pdf文档,document
        Document document = new Document();// 默认PageSize.A4, 36, 36, 36, 36
        // 解析器
        PdfWriter.getInstance(document, new FileOutputStream("./doc/11.pdf"));
        document.open();
        Paragraph title = new Paragraph("一级标题", getChineseFont());
        Chapter chapter = new Chapter(title, 1);

        Paragraph title2 = new Paragraph("二级标题-1", getChineseFont());
        Section section = chapter.addSection(title2);
        section.setBookmarkTitle("sectionName");// 左边目录显示的名字，不写就默认名
        section.setIndentation(30);
        section.setIndentationLeft(5);
        section.setBookmarkOpen(false);
        section.setNumberStyle(Section.NUMBERSTYLE_DOTTED_WITHOUT_FINAL_DOT);

        Section section2 = chapter.addSection(new Paragraph("二级标题-2", getChineseFont()));
        section2.setIndentation(30);
        section2.setIndentationLeft(5);
        section2.setBookmarkOpen(false);
        section2.setNumberStyle(Section.NUMBERSTYLE_DOTTED_WITHOUT_FINAL_DOT);

        Section subsection = section.addSection(new Paragraph("三级标题-1", getChineseFont()));
        subsection.setIndentationLeft(10);
        // subsection.setNumberDepth(1);
        subsection.setNumberStyle(Section.NUMBERSTYLE_DOTTED);

        Section subsection2 = section2.addSection(new Paragraph("三级标题-2", getChineseFont()));
        subsection2.setIndentationLeft(10);
        subsection2.setNumberStyle(Section.NUMBERSTYLE_DOTTED);
        document.add(chapter);

        document.close();
    }


    /**
     * PdfOutline - 目录 / 书签 更每一页相关
     * @throws Exception
     * PdfOutline(PdfOutline parent, PdfAction action, String title, boolean open)
     *
     * parent：根目录
     *
     * PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("./doc/11.pdf"));
     * PdfContentByte cb = writer.getDirectContent();
     *
     * //获取外部根目录
     * PdfOutline root = cb.getRootOutline();
     *
     * PdfAction：pdf点击事件，title：标题，open：是否打开
     */
    public static void testPdfOutline() throws Exception {
        // 1-创建一个pdf文档,document
        Document document = new Document();// 默认PageSize.A4, 36, 36, 36, 36
        // 解析器
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("./doc/11.pdf"));
        document.open();

        document.add(new Chunk("Chapter 1").setLocalDestination("1"));
        document.newPage();

        document.add(new Chunk("Chapter 2").setLocalDestination("2"));
        document.add(new Paragraph(new Chunk("Sub 2.1").setLocalDestination("2.1")));
        document.add(new Paragraph(new Chunk("Sub 2.2").setLocalDestination("2.2")));

        document.newPage();

        document.add(new Chunk("Chapter 3").setLocalDestination("3"));

        //内容对象
        PdfContentByte cb = writer.getDirectContent();
        //获取外部根目录
        PdfOutline root = cb.getRootOutline();
        //一级目录
        new PdfOutline(root, PdfAction.gotoLocalPage("1", false), "Chapter 1");
        //一级目录
        PdfOutline oline2 = new PdfOutline(root, PdfAction.gotoLocalPage("2", false), "Chapter 2");
        //是否打开
        oline2.setOpen(false);
        //添加二级子目录
        new PdfOutline(oline2, PdfAction.gotoLocalPage("2.1", false), "Sub 2.1");
        new PdfOutline(oline2, PdfAction.gotoLocalPage("2.2", false), "Sub 2.2");
        //添加三级目录
        new PdfOutline(root, PdfAction.gotoLocalPage("3", false), "Chapter 3");

        document.close();
    }

    /**
     * Header, Footer
     * @throws Exception
     */
    public static void testHeadFooter() throws Exception{
        Document doc = new Document();
        PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("./doc/12.pdf"));
        writer.setPageEvent(new PdfPageEventHelper() {
            public void onEndPage(PdfWriter writer, Document document) {
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
                float x = document.top(-20);
                //左
                cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"顶部-左",document.left(), x, 0);
                //中
                cb.showTextAligned(PdfContentByte.ALIGN_CENTER,writer.getPageNumber()+ " page",(document.right() + document.left())/2,x, 0);
                //右
                cb.showTextAligned(PdfContentByte.ALIGN_RIGHT,"顶部-右",document.right(), x, 0);
                //Footer
                float y = document.bottom(-20);
                //左
                cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"底部-左",document.left(), y, 0);
                //中
                cb.showTextAligned(PdfContentByte.ALIGN_CENTER,writer.getPageNumber()+" page",(document.right() + document.left())/2, y, 0);
                //右
                cb.showTextAligned(PdfContentByte.ALIGN_RIGHT,"底部-右",document.right(), y, 0);
                cb.endText();
                cb.restoreState();
            }
        });
        doc.open();
        doc.add(new Paragraph("1 page"));
        doc.close();
    }
    public static void main(String[] args) throws Exception{

//        createPdf();
//        getRectangle();
//        testChunk();
//        testPhrase();
//        testParagraph();
//        testList();
//        testImage();
//        testAnchor();
//        testChapterAndSection();
//        testPdfOutline();
        testHeadFooter();
    }


}
