package dream.pers.priv.notice.service.spring;

import common.bean.User;
import dream.pers.priv.info.dto.MaMyInfoCommonDTO;
import dream.pers.priv.notice.dao.PersPrivNoticeDetailDAO;
import dream.pers.priv.notice.dto.PersPrivNoticeDetailDTO;
import dream.pers.priv.notice.service.PersPrivNoticeDetailService;

/**
 * Notice 설정 - 상세 ServiceImpl
 * @author  nhkim8548
 * @version $Id$
 * @since   1.0
 * 
 * @spring.bean id="persPrivNoticeDetailServiceTarget"
 * @spring.txbn id="persPrivNoticeDetailService"
 * @spring.property name="persPrivNoticeDetailDAO" ref="persPrivNoticeDetailDAO"
 */
public class PersPrivNoticeDetailServiceImpl implements PersPrivNoticeDetailService
{
    private PersPrivNoticeDetailDAO persPrivNoticeDetailDAO = null;
    
    public PersPrivNoticeDetailDAO getPersPrivNoticeDetailDAO() {
		return persPrivNoticeDetailDAO;
	}

	public void setPersPrivNoticeDetailDAO(PersPrivNoticeDetailDAO persPrivNoticeDetailDAO) {
		this.persPrivNoticeDetailDAO = persPrivNoticeDetailDAO;
	}

	public PersPrivNoticeDetailDTO findDetail(MaMyInfoCommonDTO maMyInfoCommonDTO, User user)throws Exception
    {
        return persPrivNoticeDetailDAO.findDetail(maMyInfoCommonDTO, user);
    }
    
	public int insertDetail(PersPrivNoticeDetailDTO persPrivNoticeDetailDTO, MaMyInfoCommonDTO maMyInfoCommonDTO, User user) throws Exception
	{        
	    System.out.println("이거 머니??::::::::::"+ persPrivNoticeDetailDTO.getAlarmEmpSetId());
	    return persPrivNoticeDetailDAO.insertDetail( persPrivNoticeDetailDTO, maMyInfoCommonDTO, user);
	}
	
	public int updateDetail(PersPrivNoticeDetailDTO persPrivNoticeDetailDTO, User user) throws Exception
    {        
        return persPrivNoticeDetailDAO.updateDetail(persPrivNoticeDetailDTO, user);
    }
}
