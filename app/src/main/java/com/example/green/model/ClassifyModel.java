package com.example.green.model;

import com.example.green.base.ICommonModel;
import com.example.green.base.ICommonView;
import com.example.green.config.ApiConfig;
import com.example.green.net.NetManager;

public class ClassifyModel implements ICommonModel {
    @Override
    public void getData(ICommonView view, int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.URL_ALLCLASSIFY: // 分类首页
                NetManager.getNetManager().netMethod(NetManager.getNetManager()
                        .getHttpService()
                        .getClassifyList(), view, whichApi, 0);
                break;
            case ApiConfig.URL_RIGHTCLASSIFY: // 右侧分类
                int id = (int) t[0];
                NetManager.getNetManager().netMethod(NetManager.getNetManager()
                        .getHttpService()
                        .getRightClassifyList(id), view, whichApi, 0);
                break;

        }
    }
}
