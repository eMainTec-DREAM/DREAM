package dream.work.list.service.spring;

import common.bean.User;
import dream.work.list.dao.MaWoResultPointDetailDAO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultPointDetailDTO;
import dream.work.list.service.MaWoResultPointDetailService;

/**
 * 작업결과 검사항목
 * @author kim2107
 * @version $Id: MaWoResultPointDetailServiceImpl.java,v 1.0 2015/12/04 09:10:27 kim2107 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maWoResultPointDetailServiceTarget"
 * @spring.txbn id="maWoResultPointDetailService"
 * @spring.property name="maWoResultPointDetailDAO" ref="maWoResultPointDetailDAO"
 */
public class MaWoResultPointDetailServiceImpl implements MaWoResultPointDetailService
{
    private MaWoResultPointDetailDAO maWoResultPointDetailDAO = null;
    
    public MaWoResultPointDetailDAO getMaWoResultPointDetailDAO() {
		return maWoResultPointDetailDAO;
	}

	public void setMaWoResultPointDetailDAO(MaWoResultPointDetailDAO maWoResultPointDetailDAO) {
		this.maWoResultPointDetailDAO = maWoResultPointDetailDAO;
	}

	public MaWoResultPointDetailDTO findDetail(String wkOrId, String woPointId, String pmPointId, User user)throws Exception
    {
        return maWoResultPointDetailDAO.findDetail(wkOrId, woPointId, pmPointId, user);
    }
    
	public int updateDetail(MaWoResultPointDetailDTO maWoResultPointDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User user) throws Exception
    {        
		int rtnValue = 0;
		boolean inputFlag = false;
		
		if("".equals(maWoResultPointDetailDTO.getWoPointId()) || maWoResultPointDetailDTO.getWoPointId() == null )
		{
			maWoResultPointDetailDTO.setWoPointId(maWoResultPointDetailDAO.getNextSequence("SQAWOPOINT_ID"));
			inputFlag = true;
		}
		
		rtnValue += maWoResultPointDetailDAO.updateDetail(maWoResultPointDetailDTO, maWoResultMstrCommonDTO,inputFlag, user);
		
		return rtnValue;
    }
	public int insertDetail(MaWoResultPointDetailDTO maWoResultPointDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO) throws Exception
    {        
        return maWoResultPointDetailDAO.insertDetail( maWoResultPointDetailDTO, maWoResultMstrCommonDTO);
    }
}
