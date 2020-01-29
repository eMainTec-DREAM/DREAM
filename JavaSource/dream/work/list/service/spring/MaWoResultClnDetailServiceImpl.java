package dream.work.list.service.spring;

import dream.work.list.dao.MaWoResultClnDetailDAO;
import dream.work.list.dto.MaWoResultClnDetailDTO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.service.MaWoResultClnDetailService;

/**
 * 작업결과 작업설비 상세 
 * @author kim2107
 * @version $Id: MaWoResultClnDetailServiceImpl.java,v 1.0 2015/12/04 09:10:27 kim2107 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maWoResultClnDetailServiceTarget"
 * @spring.txbn id="maWoResultClnDetailService"
 * @spring.property name="maWoResultClnDetailDAO" ref="maWoResultClnDetailDAO"
 */
public class MaWoResultClnDetailServiceImpl implements MaWoResultClnDetailService
{
    private MaWoResultClnDetailDAO maWoResultClnDetailDAO = null;
    
    public MaWoResultClnDetailDAO getMaWoResultClnDetailDAO() {
		return maWoResultClnDetailDAO;
	}

	public void setMaWoResultClnDetailDAO(MaWoResultClnDetailDAO maWoResultClnDetailDAO) {
		this.maWoResultClnDetailDAO = maWoResultClnDetailDAO;
	}

	public MaWoResultClnDetailDTO findDetail(String wkOrId, String woEquipId, String compNo)throws Exception
    {
        return maWoResultClnDetailDAO.findDetail(wkOrId, woEquipId, compNo);
    }
    
	public int updateDetail(MaWoResultClnDetailDTO maWoResultClnDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO) throws Exception
    {        
        return maWoResultClnDetailDAO.updateDetail(maWoResultClnDetailDTO, maWoResultMstrCommonDTO);
    }
	public int insertDetail(MaWoResultClnDetailDTO maWoResultClnDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO) throws Exception
    {        
        return maWoResultClnDetailDAO.insertDetail( maWoResultClnDetailDTO, maWoResultMstrCommonDTO);
    }
}
