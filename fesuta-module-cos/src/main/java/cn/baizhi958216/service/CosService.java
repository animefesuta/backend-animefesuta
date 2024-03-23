package cn.baizhi958216.service;

import cn.baizhi958216.viewobject.PicVO;

public interface CosService {
    PicVO postPic(PicVO picVO);

    PicVO[] getPostsByUID(String UID);
}
