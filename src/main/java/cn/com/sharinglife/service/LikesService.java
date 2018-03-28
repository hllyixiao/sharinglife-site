package cn.com.sharinglife.service;

/**
 * @author hell
 * @data 2018/3/27 19:17
 */
public interface LikesService {

    void addLike(Integer id,Integer userId, Integer type);

    void deleteLike(Integer id,Integer userId, Integer type);
}
