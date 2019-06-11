package com.bawei.gaochenkai20190611;

/**
 * @Auther: 高晨凯
 * @Date: 2019/6/11 09:10:06
 * @Description: 契约类
 */
public interface IContent {
    /*
    V层
     */
    public interface IView {
        void getData();
    }

    /*
  P层
   */
    public interface IPersenter {
        void getData();

        //绑定类

        //解绑类
    }

    /*
  M层
   */
    public interface IModel {
        void getData();
    }


}
