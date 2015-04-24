package cn.st.rabbitmq.api;

/**
 * <p>
 * description:tags 枚举类
 * </p>
 * 
 * @author coolearth
 * @since 2015年4月24日
 * @version v1.0
 */
public enum TAGS {
    ADMINISTRATOR("administrator");

    private String tagsValue;

    private TAGS(String tagsValue) {
        this.tagsValue = tagsValue;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return tagsValue;
    }

}
