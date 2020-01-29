package dream.note.daily.service.spring;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.note.daily.dao.MaWoDailyDetailDAO;
import dream.note.daily.dto.MaWoDailyCommonDTO;
import dream.note.daily.dto.MaWoDailyDetailDTO;
import dream.note.daily.service.MaWoDailyDetailService;
import dream.pers.appreq.dto.AppReqDetailDTO;

/**
 *  - 상세 serviceimpl 
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="maWoDailyDetailServiceTarget"
 * @spring.txbn id="maWoDailyDetailService"
 * @spring.property name="maWoDailyDetailDAO" ref="maWoDailyDetailDAO"
 */
public class MaWoDailyDetailServiceImpl implements MaWoDailyDetailService
{
    private MaWoDailyDetailDAO maWoDailyDetailDAO = null;
    
    public MaWoDailyDetailDAO getMaWoDailyDetailDAO() 
    {
		return maWoDailyDetailDAO;
	}

	public void setMaWoDailyDetailDAO(MaWoDailyDetailDAO maWoDailyDetailDAO) 
	{
		this.maWoDailyDetailDAO = maWoDailyDetailDAO;
	}

	public MaWoDailyDetailDTO findDetail(MaWoDailyCommonDTO maWoDailyCommonDTO, User loginUser)throws Exception
    {
	    MaWoDailyDetailDTO maWoDailyDetailDTO = maWoDailyDetailDAO.findDetail(maWoDailyCommonDTO, loginUser);

        return maWoDailyDetailDTO;
    }
	
	public int updateDetail(MaWoDailyDetailDTO maWoDailyDetailDTO, User user) throws Exception
    {   
	    int resultCnt = 0;
	    resultCnt = maWoDailyDetailDAO.updateDetail(maWoDailyDetailDTO, user);
        return resultCnt;
    }
	
    public int insertDetail(MaWoDailyDetailDTO maWoDailyDetailDTO, User user) throws Exception
    {
        int resultCnt = 0;
        resultCnt += maWoDailyDetailDAO.insertDetail(maWoDailyDetailDTO, user);
        resultCnt += maWoDailyDetailDAO.insertBmActivities(maWoDailyDetailDTO, user);
        
        return resultCnt;
    }

    public void appProcess(AppReqDetailDTO appReqDetailDTO, User user)
    {
        /**
         * apprType : WODAY
         * apprStatus : C(결재완료), D(결재반려)
         * objectId : KEY ID
         */
        
        maWoDailyDetailDAO.setStatus(appReqDetailDTO, user);
        
    }
    public List getReportView(MaWoDailyDetailDTO maWoDailyDetailDTO, User user) {
		Map<String, Object> reportMap = null;
		List<Map<String, Object>> reportList = new ArrayList<Map<String, Object>>();
		
		List detailList = maWoDailyDetailDAO.findReportWoDetail(maWoDailyDetailDTO);
     	reportMap = (Map)detailList.get(0);
     	if ("140".equals(user.getCompNo())&&"SLP".equals(user.getPlant())) {
     		reportMap.put("WO_LIST1", maWoDailyDetailDAO.findReportSLPWorkList1(maWoDailyDetailDTO, user));
     		reportMap.put("WO_LIST2", maWoDailyDetailDAO.findReportSLPWorkList2(maWoDailyDetailDTO, user));
		}else{
			reportMap.put("WO_LIST", maWoDailyDetailDAO.findReportWorkList(maWoDailyDetailDTO));
		}
        
     	reportList.add((Map)reportMap);

	     return reportList;
	}

	public int updateStatus(MaWoDailyDetailDTO maWoDailyDetailDTO, User user) throws Exception 
	{
        return maWoDailyDetailDAO.updateStatus(maWoDailyDetailDTO, user);
	}
    
}
