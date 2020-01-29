package dream.work.list.service.spring;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import common.bean.User;
import dream.work.list.dao.MaWoResultMstrListDAO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultMstrDetailDTO;
import dream.work.list.service.MaWoResultMstrListService;

/**
 * 작업결과 - 목록 serviceimpl
 * @author kim21017
 * @version $Id: MaWoResultMstrListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maWoResultMstrListServiceTarget"
 * @spring.txbn id="maWoResultMstrListService"
 * @spring.property name="maWoResultMstrListDAO" ref="maWoResultMstrListDAO"
 */
public class MaWoResultMstrListServiceImpl implements MaWoResultMstrListService
{
    private MaWoResultMstrListDAO maWoResultMstrListDAO = null;

    public MaWoResultMstrListDAO getMaWoResultMstrListDAO() {
		return maWoResultMstrListDAO;
	}

	public void setMaWoResultMstrListDAO(MaWoResultMstrListDAO maWoResultMstrListDAO) {
		this.maWoResultMstrListDAO = maWoResultMstrListDAO;
	}

	public List findWoResultMstrList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User user)
    {      
        return maWoResultMstrListDAO.findWoResultMstrList(maWoResultMstrCommonDTO,user);
    }
	public List findWoResultPmiMstrList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User user)
    {      
        return maWoResultMstrListDAO.findWoResultPmiMstrList(maWoResultMstrCommonDTO,user);
    }
	public List findWoResultTrMstrList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User user)
    {      
        return maWoResultMstrListDAO.findWoResultTrMstrList(maWoResultMstrCommonDTO,user);
    }
	
	public List findWoBmResultMstrList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User user)
    {      
        return maWoResultMstrListDAO.findWoBmResultMstrList(maWoResultMstrCommonDTO,user);
    }
	public List findWoPmwResultMstrList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User user)
    {      
        return maWoResultMstrListDAO.findWoPmwResultMstrList(maWoResultMstrCommonDTO,user);
    }
	public List findWoPmcResultMstrList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User user)
    {      
        return maWoResultMstrListDAO.findWoPmcResultMstrList(maWoResultMstrCommonDTO,user);
    }
	public List findWoCmResultMstrList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User user)
    {      
        return maWoResultMstrListDAO.findWoCmResultMstrList(maWoResultMstrCommonDTO,user);
    }
	public List findWoTiResultMstrList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User user)
    {      
        return maWoResultMstrListDAO.findWoTiResultMstrList(maWoResultMstrCommonDTO,user);
    }
	
	public int deleteWoResultMstr(String[] deleteRows, User user) throws Exception{
	    int result = 0;
        if(!deleteRows.equals(null) && deleteRows.length>0){
            List list = new ArrayList();
            MaWoResultMstrDetailDTO maWoResultMstrDetailDTO = new MaWoResultMstrDetailDTO();
            for(String id : deleteRows)
            {
                maWoResultMstrDetailDTO.setWkOrId(id);
                list.add(BeanUtils.cloneBean(maWoResultMstrDetailDTO));
            }
            result = maWoResultMstrListDAO.updateDeleteTagWoResultMstr(list,user).length;
            maWoResultMstrListDAO.updateEmgPart(list, user);
            //분해점검 더미 데이터 수정
            for(String id : deleteRows)
            {
                maWoResultMstrListDAO.create4wp(id,user);
            }
        }
        
        return result;
    }
	
	public List getReportView(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO) {
		Map<String, Object> reportMap = null;
        List<Map<String, Object>> reportList = new ArrayList<Map<String, Object>>();
        
        String[] wkorId = maWoResultMstrCommonDTO.getSelectedWkorId().split(",");
        //맨앞은 빈칸이라 1부터
        for (int i = 1; i < wkorId.length; i++) {
        	List detailList = maWoResultMstrListDAO.findReportWoList(wkorId[i], maWoResultMstrCommonDTO);
        	
//        	List detalLabel = maWoResultMstrListDAO.findReportWoListLabel(wkorId[i], maWoResultMstrCommonDTO);
        	reportMap = (Map)detailList.get(0);
//        	reportMap.putAll((Map)detalLabel.get(0));
        	
        	reportMap.put("CRAFT_LIST", maWoResultMstrListDAO.findReportWoCraftList(wkorId[i], maWoResultMstrCommonDTO));
         	reportMap.put("PART_LIST", maWoResultMstrListDAO.findReportWoPartList(wkorId[i], maWoResultMstrCommonDTO));
         	reportMap.put("POINT_LIST", maWoResultMstrListDAO.findReportWoPointList(wkorId[i], maWoResultMstrCommonDTO));
         	reportMap.put("EQ_LIST", maWoResultMstrListDAO.findReportWoEqList(wkorId[i], maWoResultMstrCommonDTO));
         	//DYMOS에서는 작업필수 점검항목을 표시하지않음
//         	reportMap.put("STPOINT_LIST", maWoResultMstrListDAO.findReportWoStPointList(wkorId[i], maWoResultMstrCommonDTO));
        	reportList.add((Map)reportMap);
		}
        
		return reportList;
	}

    @Override
    public String findTotalCount( MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User user, String woType)
    {
        return maWoResultMstrListDAO.findTotalCount(maWoResultMstrCommonDTO,user,woType);
    }
    
	public List findWoPmwOvhResultMstrList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User user)
    {      
        return maWoResultMstrListDAO.findWoPmwOvhResultMstrList(maWoResultMstrCommonDTO,user);
    }

	@Override
	public String getData(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User user)
	{
		maWoResultMstrCommonDTO.setExceltabNo("OHWORK");
		return maWoResultMstrListDAO.getData(maWoResultMstrCommonDTO, user);
	}
	
	public String checkWoResultMstrStatus(String wkorId, User user) {
		
		return maWoResultMstrListDAO.checkWoResultMstrStatus(wkorId, user);
	}
	
	public int delWoResultMstr(String wkorId, User user) throws Exception{
	    int result = 0;
        
	    if(!wkorId.equals(null))
            	result = maWoResultMstrListDAO.deleteWoResultMstr(wkorId, user);
        
	    return result;
    }
}