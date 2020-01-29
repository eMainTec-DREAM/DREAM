package dream.work.rpt.mabmlnchart.service.spring;

import java.util.List;

import dream.work.rpt.mabmlnchart.dao.MaBmLnChartDAO;
import dream.work.rpt.mabmlnchart.dto.MaBmLnChartDTO;
import dream.work.rpt.mabmlnchart.service.MaBmLnChartService;

/**
 * 라인고장분석 serviceimpl
 * @author kim21017
 * @version $Id: MaBmLnChartServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maBmLnChartServiceTarget"
 * @spring.txbn id="maBmLnChartService"
 * @spring.property name="maBmLnChartDAO" ref="maBmLnChartDAO"
 */
public class MaBmLnChartServiceImpl implements MaBmLnChartService
{
    private MaBmLnChartDAO maBmLnChartDAO = null;

    public MaBmLnChartDAO getMaBmLnChartDAO() {
		return maBmLnChartDAO;
	}

	public void setMaBmLnChartDAO(MaBmLnChartDAO maBmLnChartDAO) {
		this.maBmLnChartDAO = maBmLnChartDAO;
	}

	public List findBmList(MaBmLnChartDTO maBmLnChartDTO)
    {      
        return maBmLnChartDAO.findBmList(maBmLnChartDTO);
    }
	
	public List findBmChart(MaBmLnChartDTO maBmLnChartDTO)
    {      
        return maBmLnChartDAO.findBmChart(maBmLnChartDTO);
    }
}