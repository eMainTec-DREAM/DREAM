package dream.work.rpt.mawotype.service.spring;

import java.util.List;

import dream.work.rpt.mawotype.dao.MaWoTypeChartDAO;
import dream.work.rpt.mawotype.dto.MaWoTypeChartDTO;
import dream.work.rpt.mawotype.service.MaWoTypeChartService;

/**
 * 작업유형별현황 serviceimpl
 * @author kim21017
 * @version $Id: MaWoTypeChartServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maWoTypeChartServiceTarget"
 * @spring.txbn id="maWoTypeChartService"
 * @spring.property name="maWoTypeChartDAO" ref="maWoTypeChartDAO"
 */
public class MaWoTypeChartServiceImpl implements MaWoTypeChartService
{
    private MaWoTypeChartDAO maWoTypeChartDAO = null;

    public MaWoTypeChartDAO getMaWoTypeChartDAO() {
		return maWoTypeChartDAO;
	}

	public void setMaWoTypeChartDAO(MaWoTypeChartDAO maWoTypeChartDAO) {
		this.maWoTypeChartDAO = maWoTypeChartDAO;
	}

	public List findList(MaWoTypeChartDTO maWoTypeChartDTO)
    {      
        return maWoTypeChartDAO.findList(maWoTypeChartDTO);
    }
	public List findWoCntChart(MaWoTypeChartDTO maWoTypeChartDTO)
    {      
        return maWoTypeChartDAO.findWoCntChart(maWoTypeChartDTO);
    }
	public List findWoTimeChart(MaWoTypeChartDTO maWoTypeChartDTO)
    {      
        return maWoTypeChartDAO.findWoTimeChart(maWoTypeChartDTO);
    }
}