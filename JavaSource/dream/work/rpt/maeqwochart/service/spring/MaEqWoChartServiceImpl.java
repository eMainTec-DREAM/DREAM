package dream.work.rpt.maeqwochart.service.spring;

import java.util.List;

import dream.work.rpt.maeqwochart.dao.MaEqWoChartDAO;
import dream.work.rpt.maeqwochart.dto.MaEqWoChartDTO;
import dream.work.rpt.maeqwochart.service.MaEqWoChartService;

/**
 * 설비작업현황 serviceimpl
 * @author kim21017
 * @version $Id: MaEqWoChartServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maEqWoChartServiceTarget"
 * @spring.txbn id="maEqWoChartService"
 * @spring.property name="maEqWoChartDAO" ref="maEqWoChartDAO"
 */
public class MaEqWoChartServiceImpl implements MaEqWoChartService
{
    private MaEqWoChartDAO maEqWoChartDAO = null;

    public MaEqWoChartDAO getMaEqWoChartDAO() {
		return maEqWoChartDAO;
	}

	public void setMaEqWoChartDAO(MaEqWoChartDAO maEqWoChartDAO) {
		this.maEqWoChartDAO = maEqWoChartDAO;
	}

	public List findWoList(MaEqWoChartDTO maEqWoChartDTO)
    {      
        return maEqWoChartDAO.findWoList(maEqWoChartDTO);
    }
	
	public List findPmList(MaEqWoChartDTO maEqWoChartDTO)
    {      
        return maEqWoChartDAO.findPmList(maEqWoChartDTO);
    }
	
	public List findPtList(MaEqWoChartDTO maEqWoChartDTO)
    {      
        return maEqWoChartDAO.findPtList(maEqWoChartDTO);
    }
	public List findUsePtList(MaEqWoChartDTO maEqWoChartDTO)
    {      
        return maEqWoChartDAO.findUsePtList(maEqWoChartDTO);
    }
}