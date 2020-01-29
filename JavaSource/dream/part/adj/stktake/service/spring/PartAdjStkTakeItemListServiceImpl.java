package dream.part.adj.stktake.service.spring;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import common.bean.User;
import dream.part.adj.stktake.dao.PartAdjStkTakeItemListDAO;
import dream.part.adj.stktake.dto.PartAdjStkTakeCommonDTO;
import dream.part.adj.stktake.dto.PartAdjStkTakeItemDetailDTO;
import dream.part.adj.stktake.dto.PartAdjStkTakeListDTO;
import dream.part.adj.stktake.service.PartAdjStkTakeItemDetailService;
import dream.part.adj.stktake.service.PartAdjStkTakeItemListService;

/**
 * 부품실사item 목록 serviceimpl
 * @author kim21017
 * @version $Id: PartAdjStkTakeListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="partAdjStkTakeItemListServiceTarget"
 * @spring.txbn id="partAdjStkTakeItemListService"
 * @spring.property name="partAdjStkTakeItemListDAO" ref="partAdjStkTakeItemListDAO"
 * @spring.property name="partAdjStkTakeItemDetailService" ref="partAdjStkTakeItemDetailService"
 */
public class PartAdjStkTakeItemListServiceImpl implements PartAdjStkTakeItemListService
{
    private PartAdjStkTakeItemListDAO partAdjStkTakeItemListDAO = null;
    private PartAdjStkTakeItemDetailService partAdjStkTakeItemDetailService = null;

    public PartAdjStkTakeItemDetailService getPartAdjStkTakeItemDetailService()
    {
        return partAdjStkTakeItemDetailService;
    }
    public void setPartAdjStkTakeItemDetailService(
            PartAdjStkTakeItemDetailService partAdjStkTakeItemDetailService)
    {
        this.partAdjStkTakeItemDetailService = partAdjStkTakeItemDetailService;
    }
    public PartAdjStkTakeItemListDAO getPartAdjStkTakeItemListDAO() {
		return partAdjStkTakeItemListDAO;
	}
	public void setPartAdjStkTakeItemListDAO(PartAdjStkTakeItemListDAO partAdjStkTakeItemListDAO) {
		this.partAdjStkTakeItemListDAO = partAdjStkTakeItemListDAO;
	}
	
	public List findItemList(PartAdjStkTakeCommonDTO partAdjStkTakeCommonDTO, PartAdjStkTakeListDTO partAdjStkTakeListDTO, User user)
    {      
        return partAdjStkTakeItemListDAO.findItemList(partAdjStkTakeCommonDTO, partAdjStkTakeListDTO,user);
    }
	
	public int deleteItemList(String[] deleteRows, User user) throws Exception{
        int result = 0;
       /* for(int i = 0; deleteRows.length > i ; i++)
        {
            result = result + partAdjStkTakeItemListDAO.deleteItemList(deleteRows[i], deleteRowsExt[i] );
        }
        */
        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + partAdjStkTakeItemListDAO.deleteItemList(id, user);
            }
        
        return result;
    }
	@Override
	public String findTotalCount(PartAdjStkTakeCommonDTO partAdjStkTakeCommonDTO,
			PartAdjStkTakeListDTO partAdjStkTakeListDTO, User user) throws Exception {
		return partAdjStkTakeItemListDAO.findTotalCount(partAdjStkTakeCommonDTO, partAdjStkTakeListDTO, user);
	}
    @Override
    public int insertItemList(PartAdjStkTakeItemDetailDTO partAdjStkTakeItemDetailDTO, PartAdjStkTakeCommonDTO partAdjStkTakeCommonDTO, User user) throws Exception
    {
        int result = 0;
        
        JSONParser parser = new JSONParser();
        JSONArray array = (JSONArray) parser.parse(partAdjStkTakeItemDetailDTO.getMultiDesc());
        
        for(Object obj:array){
            JSONObject object = (JSONObject) obj;
            String seq = partAdjStkTakeItemListDAO.getNextSequence("SQAPTPRITEM_ID");
            partAdjStkTakeItemDetailDTO.setPtStkTakeItemId(seq);
            partAdjStkTakeItemDetailDTO.setPartId((String) object.get("PART_ID"));
            partAdjStkTakeItemDetailDTO.setPartNo((String) object.get("PART_NO"));
            partAdjStkTakeItemDetailDTO.setPartDesc((String) object.get("DESCRIPTION"));
            partAdjStkTakeItemDetailDTO.setPtSize((String) object.get("PT_SIZE"));
            
            result = result + partAdjStkTakeItemDetailService.insertDetail(partAdjStkTakeItemDetailDTO, partAdjStkTakeCommonDTO, user);
        }
        
        return result;
    }
	@Override
	public String getData(User user)
	{
		return partAdjStkTakeItemListDAO.getData(user);
	}
}

