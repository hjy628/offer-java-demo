package com.hjy.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.*;

import java.io.FileOutputStream;

/**
 * @auther: hjy
 * @Date: 2024/3/19 16:44
 * @Description:
 * 合并多个PDF
 * 1、涉及的核心类：PdfReader，PdfWriter、PdfCopy（PdfWriter的子类）
 *
 * 2、实现：2次循环
 *
 * 1）第一层循环：PDF合并的文件个数，有几个PDF需要合并。
 *
 * 2）第二层循环：一个PDF文件的页数，一个PDF有几页。将其存放在新的PDF上。
 *
 * 1）使用PdfCopy
 * 2）使用PdfWriter实现PDF合并
 */

public class MergePdfFilesDemo {

    /**
     *  1）使用PdfCopy
     * @param files
     * @param newfile
     * @return
     */
    public static boolean mergePdfFiles(String[] files, String newfile) {
        boolean retValue = false;
        Document document = null;
        try {
            document = new Document();
            PdfCopy copy = new PdfCopy(document, new FileOutputStream(newfile));
            document.open();
            for (int i = 0; i < files.length; i++) {// 几个pdf文件循环
                PdfReader reader = new PdfReader(files[i]);
                int n = reader.getNumberOfPages();
                for (int j = 1; j <= n; j++) {// 一个文件有多少页循环
                    document.newPage();
                    // 从reader读取原始pdf每一页的数据追加进新的pdf中
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
     * 2）使用PdfWriter实现PDF合并
     * @param files
     * @param savepath
     * @throws Exception
     */
    public static void mergePdf(String[] files, String savepath) throws Exception {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(savepath));
        document.open();
        PdfContentByte cb = writer.getDirectContent();// 得到层

        for (int i = 0; i < files.length; i++) {
            PdfReader reader = new PdfReader(files[i]);
            int n = reader.getNumberOfPages();
            for (int j = 1; j <= n; j++) {
                document.newPage();
                PdfImportedPage page = writer.getImportedPage(reader, j);
                // 使用writer需要使用pdf的层,然后后添加
                // 可以 放大 缩小
                cb.addTemplate(page, 0, 0);
                // 扩大比例
//                 cb.addTemplate(page, 0.6f, 0, 0, 0.6f, 0, page.getHeight() + 10);
//                 cb.addTemplate(page, 1.0f, 0, 0, 1.0f, 0, page.getHeight() + 10);
            }
        }
        document.close();
    }


    public static void main(String[] args) throws Exception{
        String[] strings = {"./doc/15.pdf","./doc/16.pdf","./doc/14.pdf"};
//        mergePdfFiles(strings,"./doc/18.pdf");
//        mergePdf(strings,"./doc/19.pdf");

    }




}
