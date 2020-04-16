package chap9.adapter;

import java.util.logging.Logger;

/**
 * @auther: hjy
 * @Date: 2020/4/16 15:30
 * @Description:  Source类  类适配器模式
 */

public class Source {

    private final static Logger logger = Logger.getLogger("Source");

    public void editTextFile(){  //text文件编辑
        logger.info("a text file editing");
    }



}
