package dream.work.list.service.spring;

import common.bean.User;
import dream.work.list.dao.MaWoResultStPointDetailDAO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultStPointDetailDTO;
import dream.work.list.service.MaWoResultStPointDetailService;

/**
 * 작업결과 작업필수검사항목
 * @author kim2107
 * @version $Id: MaWoResultStPointDetailServiceImpl.java,v 1.0 2015/12/04 09:10:27 kim2107 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maWoResultStPointDetailServiceTarget"
 * @spring.txbn id="maWoResultStPointDetailService"
 * @spring.property name="maWoResultStPointDetailDAO" ref="maWoResultStPointDetailDAO"
 */
public class MaWoResultStPointDetailServiceImpl implements MaWoResultStPointDetailService
{
    private MaWoResultStPointDetailDAO maWoResultStPointDetailDAO = null;
    
    public MaWoResultStPointDetailDAO getMaWoResultStPointDetailDAO() {
		return maWoResultStPointDetailDAO;
	}

	public void setMaWoResultStPointDetailDAO(MaWoResultStPointDetailDAO maWoResultStPointDetailDAO) {
		this.maWoResultStPointDetailDAO = maWoResultStPointDetailDAO;
	}

	public MaWoResultStPointDetailDTO findDetail(String wkOrId, String woPointId, User user)throws Exception
    {
        return maWoResultStPointDetailDAO.findDetail(wkOrId, woPointId, user);
    }
    
	public int updateDetail(MaWoResultStPointDetailDTO maWoResultStPointDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO) throws Exception
    {        
        return maWoResultStPointDetailDAO.updateDetail(maWoResultStPointDetailDTO, maWoResultMstrCommonDTO);
    }
}
