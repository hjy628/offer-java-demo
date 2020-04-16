package chap9.adapter;

import java.util.logging.Logger;

/**
 * @auther: hjy
 * @Date: 2020/4/16 15:39
 * @Description:
 */

public class ObjectAdapter implements Targetable{

    private final static Logger logger = Logger.getLogger("ObjectAdapter");
    private Source source;

    public ObjectAdapter(Source source) {
        super();
        this.source = source;
    }

    @Override
    public void editTextFile() {
        this.source.editTextFile();
    }

    @Override
    public void editWordFile() {
        logger.info("a word file editing");
    }
}
