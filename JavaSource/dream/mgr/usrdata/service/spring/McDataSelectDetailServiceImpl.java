package dream.mgr.usrdata.service.spring;

import common.bean.User;
import dream.mgr.usrdata.dao.McDataSelectDetailDAO;
import dream.mgr.usrdata.dto.McDataSelectDetailDTO;
import dream.mgr.usrdata.service.McDataSelectDetailService;

/**
 * 메뉴 - 상세 serviceimpl 
 * @author  kim21017
 * @version $Id: McDataSelectDetailServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="mcDataSelectDetailServiceTarget"
 * @spring.txbn id="mcDataSelectDetailService"
 * @spring.property name="mcDataSelectDetailDAO" ref="mcDataSelectDetailDAO"
 */
public class McDataSelectDetailServiceImpl implements McDataSelectDetailService
{
    private McDataSelectDetailDAO mcDataSelectDetailDAO = null;
    
    public McDataSelectDetailDAO getMcDataSelectDetailDAO() {
		return mcDataSelectDetailDAO;
	}

	public void setMcDataSelectDetailDAO(McDataSelectDetailDAO mcDataSelectDetailDAO) {
		this.mcDataSelectDetailDAO = mcDataSelectDetailDAO;
	}

	public McDataSelectDetailDTO findDetail(String menuId, String lang)throws Exception
    {
        return mcDataSelectDetailDAO.findDetail(menuId, lang);
    }
    
	public int insertDetail(McDataSelectDetailDTO mcDataSelectDetailDTO,User loginUser) throws Exception
    {        
        return mcDataSelectDetailDAO.insertDetail(mcDataSelectDetailDTO, loginUser);
    }
	
	public int updateDetail(McDataSelectDetailDTO mcDataSelectDetailDTO,User loginUser) throws Exception
    {        
        return mcDataSelectDetailDAO.updateDetail(mcDataSelectDetailDTO, loginUser);
    }
}
