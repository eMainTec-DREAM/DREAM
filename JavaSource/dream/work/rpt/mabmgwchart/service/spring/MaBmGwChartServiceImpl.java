package dream.work.rpt.mabmgwchart.service.spring;

import java.util.List;

import dream.work.rpt.mabmgwchart.dao.MaBmGwChartDAO;
import dream.work.rpt.mabmgwchart.dto.MaBmGwChartDTO;
import dream.work.rpt.mabmgwchart.service.MaBmGwChartService;

/**
 * 과별고장분석 serviceimpl
 * @author kim21017
 * @version $Id: MaBmGwChartServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maBmGwChartServiceTarget"
 * @spring.txbn id="maBmGwChartService"
 * @spring.property name="maBmGwChartDAO" ref="maBmGwChartDAO"
 */
public class MaBmGwChartServiceImpl implements MaBmGwChartService
{
    private MaBmGwChartDAO maBmGwChartDAO = null;

    public MaBmGwChartDAO getMaBmGwChartDAO() {
		return maBmGwChartDAO;
	}

	public void setMaBmGwChartDAO(MaBmGwChartDAO maBmGwChartDAO) {
		this.maBmGwChartDAO = maBmGwChartDAO;
	}

	public List findBmList(MaBmGwChartDTO maBmGwChartDTO)
    {      
        return maBmGwChartDAO.findBmList(maBmGwChartDTO);
    }
	
	public List findBmChart(MaBmGwChartDTO maBmGwChartDTO)
    {      
        return maBmGwChartDAO.findBmChart(maBmGwChartDTO);
    }
}