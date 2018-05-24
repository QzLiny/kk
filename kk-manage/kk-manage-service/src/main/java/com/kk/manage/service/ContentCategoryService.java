package com.kk.manage.service;

import com.kk.manage.pojo.ContentCategory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ContentCategoryService
 * @Author Administrator
 * @Param
 * @Return
 * @Date 2018/5/5 16:52
 */
@Service
public class ContentCategoryService extends BaseService<ContentCategory> {

    public void saveContentCategory(ContentCategory contentCategory) {
        contentCategory.setId(null);
        contentCategory.setIsParent(false);
        contentCategory.setSortOrder(1);
        contentCategory.setStatus(1);

        super.save(contentCategory);

        //判断父节点的isParent是否为true，不是，需要修改为true
        ContentCategory parent = super.queryById(contentCategory.getParentId());
        if ( !parent.getIsParent() ){
            parent.setIsParent(true);
            super.updateSelective(parent);
        }
    }

    public void deleteContentCategory(ContentCategory contentCategory) {
        List<Object> ids = new ArrayList<>();
        ids.add(contentCategory.getId());
        findAllSubNode(contentCategory.getId() , ids);

        super.deleteByIds(ContentCategory.class , "id" , ids);

        ContentCategory record = new ContentCategory();
        record.setParentId(contentCategory.getParentId());
        List<ContentCategory> list = super.queryListByWhere(record);

        if(null == list || list.isEmpty()) {
            ContentCategory parent = new ContentCategory();
            parent.setId(contentCategory.getParentId());
            parent.setIsParent(false);
            super.updateSelective(parent);
        }
    }

    private void findAllSubNode(Long parentId , List<Object> ids){
        ContentCategory record = new ContentCategory();
        record.setParentId(parentId);
        List<ContentCategory> list = super.queryListByWhere(record);
        for (ContentCategory contentCategory : list) {
            ids.add(contentCategory.getId());
            //判断该节点是否为父节点，如果是，进行递归
            if (contentCategory.getIsParent()){
                findAllSubNode(contentCategory.getId() , ids);
            }
        }
    }
}