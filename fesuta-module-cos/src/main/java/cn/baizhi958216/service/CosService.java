package cn.baizhi958216.service;

import cn.baizhi958216.viewobject.PicVO;
import cn.baizhi958216.viewobject.UserWithPostVO;

public interface CosService {
    PicVO postPic(PicVO picVO);

    PicVO[] getPostsByUID(String UID);

    UserWithPostVO[] getAllAuthors();
}
