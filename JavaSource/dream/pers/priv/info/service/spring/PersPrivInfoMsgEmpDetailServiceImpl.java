package dream.pers.priv.info.service.spring;

import common.bean.User;
import dream.pers.priv.info.dao.PersPrivInfoMsgEmpDetailDAO;
import dream.pers.priv.info.dto.MaMyInfoCommonDTO;
import dream.pers.priv.info.dto.PersPrivInfoMsgEmpDetailDTO;
import dream.pers.priv.info.service.PersPrivInfoMsgEmpDetailService;

/**
 * »ó¼¼
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * 
 * @spring.bean id="persPrivInfoMsgEmpDetailServiceTarget"
 * @spring.txbn id="persPrivInfoMsgEmpDetailService"
 * @spring.property name="persPrivInfoMsgEmpDetailDAO" ref="persPrivInfoMsgEmpDetailDAO"
 */
public class PersPrivInfoMsgEmpDetailServiceImpl implements PersPrivInfoMsgEmpDetailService
{
    private PersPrivInfoMsgEmpDetailDAO persPrivInfoMsgEmpDetailDAO = null;
    
    public PersPrivInfoMsgEmpDetailDAO getPersPrivInfoMsgEmpDetailDAO() {
		return persPrivInfoMsgEmpDetailDAO;
	}

	public void setPersPrivInfoMsgEmpDetailDAO(PersPrivInfoMsgEmpDetailDAO persPrivInfoMsgEmpDetailDAO) {
		this.persPrivInfoMsgEmpDetailDAO = persPrivInfoMsgEmpDetailDAO;
	}

	public PersPrivInfoMsgEmpDetailDTO findDetail(MaMyInfoCommonDTO maMyInfoCommonDTO, User user)throws Exception
    {
        return persPrivInfoMsgEmpDetailDAO.findDetail(maMyInfoCommonDTO, user);
    }
    
	public int updateDetail(PersPrivInfoMsgEmpDetailDTO persPrivInfoMsgEmpDetailDTO, MaMyInfoCommonDTO maMyInfoCommonDTO, User user) throws Exception
    {        
        return persPrivInfoMsgEmpDetailDAO.updateDetail(persPrivInfoMsgEmpDetailDTO, maMyInfoCommonDTO, user);
    }
	public int insertDetail(PersPrivInfoMsgEmpDetailDTO persPrivInfoMsgEmpDetailDTO, MaMyInfoCommonDTO maMyInfoCommonDTO, User user) throws Exception
    {        
        return persPrivInfoMsgEmpDetailDAO.insertDetail( persPrivInfoMsgEmpDetailDTO, maMyInfoCommonDTO, user);
    }

	public String validMsgObjType(PersPrivInfoMsgEmpDetailDTO persPrivInfoMsgEmpDetailDTO, User user) throws Exception 
	{
		return persPrivInfoMsgEmpDetailDAO.validMsgObjType(persPrivInfoMsgEmpDetailDTO, user);
	}
}
