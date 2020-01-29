package dream.pers.priv.notice.service.spring;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.util.CommonUtil;
import dream.pers.priv.info.dto.MaMyInfoCommonDTO;
import dream.pers.priv.notice.dao.PersPrivNoticeListDAO;
import dream.pers.priv.notice.dto.PersPrivNoticeDetailDTO;
import dream.pers.priv.notice.service.PersPrivNoticeDetailService;
import dream.pers.priv.notice.service.PersPrivNoticeListService;

/**
 * Notice 설정 - 목록 ServiceImpl
 * @author nhkim8548
 * @version $Id$
 * @since 1.0
 * 
 * @spring.bean id="persPrivNoticeListServiceTarget"
 * @spring.txbn id="persPrivNoticeListService"
 * @spring.property name="persPrivNoticeListDAO" ref="persPrivNoticeListDAO"
 */
public class PersPrivNoticeListServiceImpl implements PersPrivNoticeListService
{
    private PersPrivNoticeListDAO persPrivNoticeListDAO = null;

    public PersPrivNoticeListDAO getPersPrivNoticeListDAO() {
        return persPrivNoticeListDAO;
    }

    public void setPersPrivNoticeListDAO(PersPrivNoticeListDAO persPrivNoticeListDAO) {
        this.persPrivNoticeListDAO = persPrivNoticeListDAO;
    }
    
    public List findList(MaMyInfoCommonDTO maMyInfoCommonDTO, User user)
    {      
        return persPrivNoticeListDAO.findList(maMyInfoCommonDTO, user);
    }
    
    @Override
    public void saveList(List<Map> gridList, User user) throws Exception 
    {
        PersPrivNoticeDetailDTO persPrivNoticeDetailDTO = null;
        PersPrivNoticeDetailService persPrivNoticeDetailService = (PersPrivNoticeDetailService)CommonUtil.getBean("persPrivNoticeDetailService", user);
        
        for(Map map : gridList)
        {
            persPrivNoticeDetailDTO = (PersPrivNoticeDetailDTO)CommonUtil.makeDTO(map, PersPrivNoticeDetailDTO.class);
            
            switch(persPrivNoticeDetailDTO.getNativeeditor())
            {
                case "inserted":
                    break;
                case "updated" : persPrivNoticeDetailService.updateDetail(persPrivNoticeDetailDTO, user);
                    break;
                case "deleted": 
                    break;
            }
            
        }
    }
    
    public String findTotalCount(MaMyInfoCommonDTO maMyInfoCommonDTO, User user) throws Exception 
    {
        return persPrivNoticeListDAO.findTotalCount(maMyInfoCommonDTO, user);
    }
}

