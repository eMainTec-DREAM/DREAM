package dream.asset.list.service.spring;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.util.CommonUtil;
import common.util.StringUtil;
import dream.asset.categ.list.dto.MaEqCatalogCommonDTO;
import dream.asset.categ.list.dto.MaEqCtgSpecDetailDTO;
import dream.asset.categ.list.dto.MaEqCtgSpecListDTO;
import dream.asset.categ.list.service.MaEqCtgSpecDetailService;
import dream.asset.categ.list.service.MaEqCtgSpecListService;
import dream.asset.list.dao.MaEqMstrSpecListDAO;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrSpecDetailDTO;
import dream.asset.list.dto.MaEqMstrSpecListDTO;
import dream.asset.list.service.MaEqMstrAsmbListService;
import dream.asset.list.service.MaEqMstrSpecDetailService;
import dream.asset.list.service.MaEqMstrSpecListService;

/**
 * 설비제원(스펙) 목록
 * @author kim21017
 * @version $Id: MaEqMstrSpecListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maEqMstrSpecListServiceTarget"
 * @spring.txbn id="maEqMstrSpecListService"
 * @spring.property name="maEqMstrSpecListDAO" ref="maEqMstrSpecListDAO"
 * @spring.property name="maEqMstrSpecDetailService" ref="maEqMstrSpecDetailService"
 */
public class MaEqMstrSpecListServiceImpl implements MaEqMstrSpecListService
{
    private MaEqMstrSpecListDAO maEqMstrSpecListDAO = null;

    private MaEqMstrSpecDetailService maEqMstrSpecDetailService = null;

	public List findSpecList(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrSpecListDTO maEqMstrSpecListDTO, User loginUser)
    {      
        return maEqMstrSpecListDAO.findSpecList(maEqMstrCommonDTO, maEqMstrSpecListDTO, loginUser);
    }

	public MaEqMstrSpecDetailService getMaEqMstrSpecDetailService() {
		return maEqMstrSpecDetailService;
	}

	public void setMaEqMstrSpecDetailService(MaEqMstrSpecDetailService maEqMstrSpecDetailService) {
		this.maEqMstrSpecDetailService = maEqMstrSpecDetailService;
	}

	public MaEqMstrSpecListDAO getMaEqMstrSpecListDAO() {
		return maEqMstrSpecListDAO;
	}

	public void setMaEqMstrSpecListDAO(MaEqMstrSpecListDAO maEqMstrSpecListDAO) {
		this.maEqMstrSpecListDAO = maEqMstrSpecListDAO;
	}
	
	public int deleteSpecList(String[] deleteRows, String compNo) throws Exception{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + maEqMstrSpecListDAO.deleteSpecList(id, compNo);
            }
        
        return result;
    }
	public String findTotalCount(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrSpecListDTO maEqMstrSpecListDTO,User user)  throws Exception
    {
        return maEqMstrSpecListDAO.findTotalCount(maEqMstrCommonDTO, maEqMstrSpecListDTO, user);
    }

	@Override
	public void saveList(List<Map> gridList, User user) throws Exception 
	{
		MaEqMstrCommonDTO maEqMstrCommonDTO = null;
		MaEqMstrSpecDetailDTO maEqMstrSpecDetailDTO = null;
		
		for(Map map : gridList)
        {
			maEqMstrCommonDTO = (MaEqMstrCommonDTO)CommonUtil.makeDTO(map, MaEqMstrCommonDTO.class);
        	maEqMstrSpecDetailDTO = (MaEqMstrSpecDetailDTO)CommonUtil.makeDTO(map, MaEqMstrSpecDetailDTO.class);
        	
        	maEqMstrCommonDTO.setCompNo(user.getCompNo());
        	
        	switch(maEqMstrSpecDetailDTO.getNativeeditor())
        	{
        		case "inserted":
        			break;
        		case "updated" : maEqMstrSpecDetailService.updateDetail(maEqMstrSpecDetailDTO,maEqMstrCommonDTO, user);
        			break;
        		case "deleted": 
        			break;
        	}
        	
        }
	}
	
	@Override
    public int inputEqCtgSpecList(MaEqCtgSpecDetailDTO maEqCtgSpecDetailDTO, String equipId, User user) throws Exception
    {
	    int result = 0;
        
        MaEqMstrAsmbListService maEqMstrAsmbListService = (MaEqMstrAsmbListService) CommonUtil.getBean("maEqMstrAsmbListService", user);
        
        //validation
        try{
            if(Integer.parseInt(equipId)<=0) {
                throw new NumberFormatException();
            }
        }catch(NumberFormatException nfe){
            nfe.printStackTrace();
            return result;
        }
        
        String eqSpecId = this.getEqSpec(equipId, maEqCtgSpecDetailDTO.getEqCtgSpecId(), user);
        String eqAsmbId = "";
        if(!"".equals(maEqCtgSpecDetailDTO.getEqCtgAsmbId()) && !"0".equals(maEqCtgSpecDetailDTO.getEqCtgAsmbId())){
            eqAsmbId = maEqMstrAsmbListService.getEqAsmb(equipId, maEqCtgSpecDetailDTO.getEqCtgAsmbId(), user);
        }
        
        if(eqSpecId == null || "".equals(eqSpecId))
        {
            MaEqMstrCommonDTO maEqMstrCommonDTO = new MaEqMstrCommonDTO();
            maEqMstrCommonDTO.setEquipId(equipId);
            MaEqMstrSpecDetailDTO maEqMstrSpecDetailDTO = new MaEqMstrSpecDetailDTO();
            maEqMstrSpecDetailDTO.setEqSpecId(maEqMstrSpecListDAO.getNextSequence("SQAEQSPEC_ID"));
            maEqMstrSpecDetailDTO.setEqAsmbId(eqAsmbId);
            maEqMstrSpecDetailDTO.setCateg(maEqCtgSpecDetailDTO.getCateg());
            maEqMstrSpecDetailDTO.setPrompt(maEqCtgSpecDetailDTO.getPrompt());
            maEqMstrSpecDetailDTO.setUom(maEqCtgSpecDetailDTO.getUom());
            maEqMstrSpecDetailDTO.setOrdNo(maEqCtgSpecDetailDTO.getOrdNo());
            maEqMstrSpecDetailDTO.setEqCtgSpecId(maEqCtgSpecDetailDTO.getEqCtgSpecId());
            maEqMstrSpecDetailDTO.setIsEqTypeSpec(maEqCtgSpecDetailDTO.getIsEqTypeSpec());
            result += maEqMstrSpecDetailService.insertDetail(maEqMstrSpecDetailDTO, maEqMstrCommonDTO, user);
        }
        else
        {
            MaEqMstrCommonDTO maEqMstrCommonDTO = new MaEqMstrCommonDTO();
            maEqMstrCommonDTO.setEquipId(equipId);
            MaEqMstrSpecListDTO maEqMstrSpecListDTO = new MaEqMstrSpecListDTO();
            maEqMstrSpecListDTO.setEqSpecId(eqSpecId);
            MaEqMstrSpecDetailDTO maEqMstrSpecDetailDTO = maEqMstrSpecDetailService.findDetail(maEqMstrSpecListDTO, maEqMstrCommonDTO, user);
            maEqMstrSpecDetailDTO.setEqAsmbId(eqAsmbId);
            maEqMstrSpecDetailDTO.setCateg(maEqCtgSpecDetailDTO.getCateg());
            maEqMstrSpecDetailDTO.setPrompt(maEqCtgSpecDetailDTO.getPrompt());
            maEqMstrSpecDetailDTO.setUom(maEqCtgSpecDetailDTO.getUom());
            maEqMstrSpecDetailDTO.setOrdNo(maEqCtgSpecDetailDTO.getOrdNo());
            maEqMstrSpecDetailDTO.setEqCtgSpecId(maEqCtgSpecDetailDTO.getEqCtgSpecId());
            maEqMstrSpecDetailDTO.setIsEqTypeSpec(maEqCtgSpecDetailDTO.getIsEqTypeSpec());
            result += maEqMstrSpecDetailService.updateDetail(maEqMstrSpecDetailDTO, maEqMstrCommonDTO, user);
        }
        
        return result;
    }

	@Override
	public int inputEqCtgSpecList(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrSpecDetailDTO maEqMstrSpecDetailDTO, User user) throws Exception 
	{
	    MaEqCtgSpecDetailService maEqCtgSpecDetailService = (MaEqCtgSpecDetailService) CommonUtil.getBean("maEqCtgSpecDetailService", user);
	    
		int result = 0;
        
		String equipId = maEqMstrCommonDTO.getEquipId();
        String[] eqCtgSpecIds = maEqMstrSpecDetailDTO.getMultiKey().split("\\+");
        
        for(String eqCtgSpecId:eqCtgSpecIds) {
            MaEqCtgSpecListDTO maEqCtgSpecListDTO = new MaEqCtgSpecListDTO();
            maEqCtgSpecListDTO.setEqCtgSpecId(eqCtgSpecId);
            MaEqCtgSpecDetailDTO maEqCtgSpecDetailDTO = maEqCtgSpecDetailService.findDetail(maEqCtgSpecListDTO, user);
            
            result += this.inputEqCtgSpecList(maEqCtgSpecDetailDTO, equipId, user);
        }
        
        return result;
	}

    @Override
    public int inputEqCtgSpecList(String eqCtgId, String equipId, User user) throws Exception
    {
        MaEqCtgSpecListService maEqCtgSpecListService = (MaEqCtgSpecListService) CommonUtil.getBean("maEqCtgSpecListService", user);
        
        int result = 0;
        
        //validation
        try{
            if(Integer.parseInt(equipId)<=0 || Integer.parseInt(eqCtgId)<=0) {
                throw new NumberFormatException();
            }
        }catch(NumberFormatException nfe){
            nfe.printStackTrace();
            return result;
        }
        
        // 유사설비공통제원여부 N
        this.unlinkOldEqSpec(equipId, user);
        // 변경된 설비종류 표준 제원 추가
        MaEqCatalogCommonDTO maEqCatalogCommonDTO = new MaEqCatalogCommonDTO();
        maEqCatalogCommonDTO.setEqCtgId(eqCtgId);
        List<Map> eqCtgSpecList = maEqCtgSpecListService.findSpecList(maEqCatalogCommonDTO, new MaEqCtgSpecListDTO(), user);
        
        for(Map eqCtgSpec:eqCtgSpecList){
            MaEqCtgSpecDetailDTO maEqCtgSpecDetailDTO = (MaEqCtgSpecDetailDTO) CommonUtil.makeDTO(eqCtgSpec, MaEqCtgSpecDetailDTO.class);
            
            if("Y".equals(maEqCtgSpecDetailDTO.getIsEqTypeSpec())) {
                result += this.inputEqCtgSpecList(maEqCtgSpecDetailDTO, equipId, user);
            }
        }
        
        return result;
    }
    
    private String getEqSpec(String equipId, String eqCtgSpecId, User user)
    {
        String eqSpecId = "";
        
        //validation
        try{
            if(Integer.parseInt(equipId)<=0 || Integer.parseInt(eqCtgSpecId)<=0){
                throw new NumberFormatException();
            }
        }catch(NumberFormatException nfe){
            nfe.printStackTrace();
            return eqSpecId;
        }
        
        MaEqMstrCommonDTO maEqMstrCommonDTO = new MaEqMstrCommonDTO();
        maEqMstrCommonDTO.setEquipId(equipId);
        MaEqMstrSpecListDTO maEqMstrSpecListDTO = new MaEqMstrSpecListDTO();
        maEqMstrSpecListDTO.setEqCtgSpecId(eqCtgSpecId);
        List<Map> eqSpecList = maEqMstrSpecListDAO.findSpecList(maEqMstrCommonDTO, maEqMstrSpecListDTO, user);
        if(eqSpecList.size()>0) eqSpecId = StringUtil.valueOf(eqSpecList.get(0).get("EQSPECID"));
        
        return eqSpecId;
    }
    
    @Override
    public int unlinkOldEqSpec(String equipId, User user) throws Exception
    {
        int result = 0;
        
        //validation
        try{
            if(Integer.parseInt(equipId)<=0) {
                throw new NumberFormatException();
            }
        }catch(NumberFormatException nfe){
            nfe.printStackTrace();
            return result;
        }
        
        MaEqMstrCommonDTO maEqMstrCommonDTO = new MaEqMstrCommonDTO();
        maEqMstrCommonDTO.setEquipId(equipId);
        List<Map> eqSpecList = maEqMstrSpecListDAO.findSpecList(maEqMstrCommonDTO, new MaEqMstrSpecListDTO(), user);
        
        for(Map eqSpec:eqSpecList){
            MaEqMstrSpecDetailDTO maEqMstrSpecDetailDTO = (MaEqMstrSpecDetailDTO) CommonUtil.makeDTO(eqSpec, MaEqMstrSpecDetailDTO.class);
            maEqMstrSpecDetailDTO.setIsEqTypeSpec("N");
            result += maEqMstrSpecDetailService.updateDetail(maEqMstrSpecDetailDTO, maEqMstrCommonDTO, user);
        }
        
        return result;
    }

    @Override
    public int undoEqCtgSpec(String eqCtgSpecId, User user) throws Exception
    {
        int result = 0;
        
        //validation
        try{
            if(Integer.parseInt(eqCtgSpecId)<=0) {
                throw new NumberFormatException();
            }
        }catch(NumberFormatException nfe){
            nfe.printStackTrace();
            return result;
        }
        
        MaEqMstrCommonDTO maEqMstrCommonDTO = new MaEqMstrCommonDTO();
        MaEqMstrSpecListDTO maEqMstrSpecListDTO = new MaEqMstrSpecListDTO();
        maEqMstrSpecListDTO.setEqCtgSpecId(eqCtgSpecId);
        List<Map> eqSpecList = maEqMstrSpecListDAO.findSpecList(maEqMstrCommonDTO, maEqMstrSpecListDTO, user);
        
        for(Map eqSpec:eqSpecList){
            MaEqMstrSpecDetailDTO maEqMstrSpecDetailDTO = (MaEqMstrSpecDetailDTO) CommonUtil.makeDTO(eqSpec, MaEqMstrSpecDetailDTO.class);
            maEqMstrSpecDetailDTO.setIsEqTypeSpec("N");
            result += maEqMstrSpecDetailService.updateDetail(maEqMstrSpecDetailDTO, maEqMstrCommonDTO, user);
        }
        
        return result;
    }
}

