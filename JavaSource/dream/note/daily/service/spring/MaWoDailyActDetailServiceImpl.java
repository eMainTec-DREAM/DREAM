package dream.note.daily.service.spring;

import dream.note.daily.dao.MaWoDailyActDetailDAO;
import dream.note.daily.dto.MaWoDailyActDetailDTO;
import dream.note.daily.dto.MaWoDailyCommonDTO;
import dream.note.daily.service.MaWoDailyActDetailService;

/**
 * 일일작업 - Main Activities
 * @author kim2107
 * @version $Id: MaWoDailyActDetailServiceImpl.java,v 1.0 2015/12/04 09:10:27 kim2107 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maWoDailyActDetailServiceTarget"
 * @spring.txbn id="maWoDailyActDetailService"
 * @spring.property name="maWoDailyActDetailDAO" ref="maWoDailyActDetailDAO"
 */
public class MaWoDailyActDetailServiceImpl implements MaWoDailyActDetailService
{
    private MaWoDailyActDetailDAO maWoDailyActDetailDAO = null;
    
    public MaWoDailyActDetailDAO getMaWoDailyActDetailDAO() {
		return maWoDailyActDetailDAO;
	}

	public void setMaWoDailyActDetailDAO(MaWoDailyActDetailDAO maWoDailyActDetailDAO) {
		this.maWoDailyActDetailDAO = maWoDailyActDetailDAO;
	}

	public MaWoDailyActDetailDTO findDetail(String woDayListId, String woDayActId, String compNo)throws Exception
    {
        return maWoDailyActDetailDAO.findDetail(woDayListId, woDayActId, compNo);
    }
    
	public int updateDetail(MaWoDailyActDetailDTO maWoDailyActDetailDTO, MaWoDailyCommonDTO maWoDailyCommonDTO) throws Exception
    {        
        return maWoDailyActDetailDAO.updateDetail(maWoDailyActDetailDTO, maWoDailyCommonDTO);
    }
	public int insertDetail(MaWoDailyActDetailDTO maWoDailyActDetailDTO, MaWoDailyCommonDTO maWoDailyCommonDTO) throws Exception
    {        
        return maWoDailyActDetailDAO.insertDetail( maWoDailyActDetailDTO, maWoDailyCommonDTO);
    }
}
