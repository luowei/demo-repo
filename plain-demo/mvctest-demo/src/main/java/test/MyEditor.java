package test;

import java.beans.PropertyEditorSupport;

/**
 * @className:MyEditor.java
 * @classDescription:自定义一个属性编辑器
 * @author:wei.luo
 * @createTime:2012-2-13上午02:10:17
 */
public class MyEditor extends PropertyEditorSupport {

    @Override
    public String getAsText() {
        // TODO Auto-generated method stub
        return super.getAsText();
    }

    @Override
    public void setValue(Object value) {
        // TODO Auto-generated method stub
        super.setValue(value);
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        // TODO Auto-generated method stub
        super.setAsText(text);
    }

}
