package khmerhowto.Service;

import khmerhowto.Repository.Model.Interested;
import khmerhowto.Repository.Model.Role;

import java.util.List;

public interface InterestedService {
    public Integer getTotalLike(Integer id);
    List<Interested> findByuserIdAndContentId(int u_id, int c_id);
}
