package dream.part.list.service.spring;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.util.CommonUtil;
import dream.part.list.dao.PartListBinListDAO;
import dream.part.list.dto.PartListBinDetailDTO;
import dream.part.list.dto.PartListBinListDTO;
import dream.part.list.service.PartListBinDetailService;
import dream.part.list.service.PartListBinListService;

/**
 * 부품창고 보관위치 - List Service implements
 * @author cjscjs9
 * @version $Id:$
 * @since 1.0
 * @spring.bean id="partListBinListServiceTarget"
 * @spring.txbn id="partListBinListService"
 * @spring.property name="partListBinListDAO" ref="partListBinListDAO"
 */
public class PartListBinListServiceImpl implements PartListBinListService
{
	private PartListBinListDAO partListBinListDAO = null;

	public List findPtWhBinList(PartListBinListDTO partListBinListDTO, User user) throws Exception
    {      
        return partListBinListDAO.findPtWhBinList(partListBinListDTO,user);
    }

	public int deletePtWhBinList( String[] deleteRows, User user) throws Exception
	{
        int result = 0;
        
        PartListBinListDTO partListBinListDTO = new PartListBinListDTO();
        PartListBinDetailService partListBinDetailService = (PartListBinDetailService)CommonUtil.getBean("partListBinDetailService", user.getCompNo());

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
            	partListBinListDTO.setPtwhBinNoId(id);
            	// 삭제하기전 보관위치 정보 조회
            	List ptWhBinList = partListBinListDAO.findPtWhBinList(partListBinListDTO,user);
                
                // 보관위치 정보 삭제
                result = result + partListBinListDAO.deletePtWhBinList(id, user);
                
                // 삭제 후 재고 저장위치 갱신처리
                if(ptWhBinList.size() != 0)
                {
                	for (int i = 0; i < ptWhBinList.size(); i++)
                	{
                		Map map = (Map)ptWhBinList.get(i);
                		PartListBinDetailDTO partListBinDetailDTO = (PartListBinDetailDTO) CommonUtil.makeDTO(map, PartListBinDetailDTO.class);
                		
                		partListBinDetailService.updateBinNo(partListBinDetailDTO, user);
					}
                }
            }
        
        
        return result;
    }

	public PartListBinListDAO getPartListBinListDAO() {
		return partListBinListDAO;
	}

	public void setPartListBinListDAO(PartListBinListDAO partListBinListDAO) {
		this.partListBinListDAO = partListBinListDAO;
	}
	public String findTotalCount(PartListBinListDTO partListBinListDTO,User user) throws Exception
    {
        return partListBinListDAO.findTotalCount(partListBinListDTO, user);
    }
}

