package dream.asset.list.service.spring;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.util.CommonUtil;
import common.util.StringUtil;
import dream.asset.categ.list.dto.MaEqCatalogCommonDTO;
import dream.asset.categ.list.dto.MaEqCtgAsmbDetailDTO;
import dream.asset.categ.list.dto.MaEqCtgAsmbListDTO;
import dream.asset.categ.list.service.MaEqCtgAsmbDetailService;
import dream.asset.categ.list.service.MaEqCtgAsmbListService;
import dream.asset.list.dao.MaEqMstrAsmbListDAO;
import dream.asset.list.dto.MaEqMstrAsmbDetailDTO;
import dream.asset.list.dto.MaEqMstrAsmbListDTO;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.service.MaEqMstrAsmbDetailService;
import dream.asset.list.service.MaEqMstrAsmbListService;

/**
 * 설비부위 목록
 * @author kim21017
 * @version $Id: MaEqMstrAsmbListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maEqMstrAsmbListServiceTarget"
 * @spring.txbn id="maEqMstrAsmbListService"
 * @spring.property name="maEqMstrAsmbListDAO" ref="maEqMstrAsmbListDAO"
 * @spring.property name="maEqMstrAsmbDetailService" ref="maEqMstrAsmbDetailService"
 */
public class MaEqMstrAsmbListServiceImpl implements MaEqMstrAsmbListService
{
    private MaEqMstrAsmbListDAO   maEqMstrAsmbListDAO   = null;
    
    private MaEqMstrAsmbDetailService maEqMstrAsmbDetailService = null;

	public MaEqMstrAsmbDetailService getMaEqMstrAsmbDetailService() {
		return maEqMstrAsmbDetailService;
	}

	public void setMaEqMstrAsmbDetailService(MaEqMstrAsmbDetailService maEqMstrAsmbDetailService) {
		this.maEqMstrAsmbDetailService = maEqMstrAsmbDetailService;
	}

	public MaEqMstrAsmbListDAO getMaEqMstrAsmbListDAO() {
		return maEqMstrAsmbListDAO;
	}

	public void setMaEqMstrAsmbListDAO(MaEqMstrAsmbListDAO maEqMstrAsmbListDAO) {
		this.maEqMstrAsmbListDAO = maEqMstrAsmbListDAO;
	}

	public List findAsmbList(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrAsmbListDTO maEqMstrAsmbListDTO, User loginUser)
    {      
        return maEqMstrAsmbListDAO.findAsmbList(maEqMstrCommonDTO, maEqMstrAsmbListDTO, loginUser);
    }
	
	public int deleteAsmbList(String[] deleteRows, String compNo) throws Exception{
        int result = 0;

        if(!deleteRows.equals(null)){
        	for(String id : deleteRows)
            {
                result = result + maEqMstrAsmbListDAO.deleteAsmbList(id, compNo);
            }
        }
        return result;
    }
	
	@Override
    public int inputAsmb(MaEqCtgAsmbDetailDTO maEqCtgAsmbDetailDTO, String equipId, User user) throws Exception
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
	    
	    String eqAsmbId = this.getEqAsmb(equipId, maEqCtgAsmbDetailDTO.getEqCtgAsmbId(), user);
	    String pEqAsmbId = "0";
	    if(!"".equals(maEqCtgAsmbDetailDTO.getPeqCtgAsmbId()) && !"0".equals(maEqCtgAsmbDetailDTO.getPeqCtgAsmbId())){
	        pEqAsmbId = this.getEqAsmb(equipId, maEqCtgAsmbDetailDTO.getPeqCtgAsmbId(), user);
	    }
        
        if(eqAsmbId == null || "".equals(eqAsmbId))
        {
            eqAsmbId = maEqMstrAsmbListDAO.getNextSequence("SQAEQASMB_ID");
            MaEqMstrCommonDTO maEqMstrCommonDTO = new MaEqMstrCommonDTO();
            maEqMstrCommonDTO.setEquipId(equipId);
            MaEqMstrAsmbDetailDTO maEqMstrAsmbDetailDTO = new MaEqMstrAsmbDetailDTO();
            maEqMstrAsmbDetailDTO.setEqAsmbId(eqAsmbId);
            maEqMstrAsmbDetailDTO.setEqAsmbDesc(maEqCtgAsmbDetailDTO.getEqCtgAsmbDesc());
            maEqMstrAsmbDetailDTO.setPeqAsmbId(pEqAsmbId);
            maEqMstrAsmbDetailDTO.setEqAsmbNo(eqAsmbId);
            maEqMstrAsmbDetailDTO.setEqCtgId(maEqCtgAsmbDetailDTO.getEqCtgId());
            maEqMstrAsmbDetailDTO.setEqCtgAsmbId(maEqCtgAsmbDetailDTO.getEqCtgAsmbId());
            maEqMstrAsmbDetailDTO.setPeqCtgAsmbId(maEqCtgAsmbDetailDTO.getPeqCtgAsmbId());
            maEqMstrAsmbDetailDTO.setEqCtgAsmbNo(maEqCtgAsmbDetailDTO.getEqCtgAsmbNo());
            maEqMstrAsmbDetailDTO.setIsEqTypeAsmb(maEqCtgAsmbDetailDTO.getIsEqTypeAsmb());
            maEqMstrAsmbDetailDTO.setOrdNo(maEqCtgAsmbDetailDTO.getOrdNo());
            maEqMstrAsmbDetailDTO.setIsUse(maEqCtgAsmbDetailDTO.getIsUse());
            maEqMstrAsmbDetailDTO.setRemark(maEqCtgAsmbDetailDTO.getRemark());
            
            result += maEqMstrAsmbDetailService.insertDetail(maEqMstrAsmbDetailDTO, maEqMstrCommonDTO, user);
        }
        else
        {
            MaEqMstrCommonDTO maEqMstrCommonDTO = new MaEqMstrCommonDTO();
            maEqMstrCommonDTO.setEquipId(equipId);
            MaEqMstrAsmbListDTO maEqMstrAsmbListDTO = new MaEqMstrAsmbListDTO();
            maEqMstrAsmbListDTO.setEqAsmbId(eqAsmbId);
            MaEqMstrAsmbDetailDTO maEqMstrAsmbDetailDTO = maEqMstrAsmbDetailService.findDetail(maEqMstrAsmbListDTO, maEqMstrCommonDTO, user);
            maEqMstrAsmbDetailDTO.setEqAsmbDesc(maEqCtgAsmbDetailDTO.getEqCtgAsmbDesc());
            maEqMstrAsmbDetailDTO.setPeqAsmbId(pEqAsmbId);
            maEqMstrAsmbDetailDTO.setPeqCtgAsmbId(maEqCtgAsmbDetailDTO.getPeqCtgAsmbId());
            maEqMstrAsmbDetailDTO.setEqCtgAsmbNo(maEqCtgAsmbDetailDTO.getEqCtgAsmbNo());
            maEqMstrAsmbDetailDTO.setIsEqTypeAsmb(maEqCtgAsmbDetailDTO.getIsEqTypeAsmb());
            maEqMstrAsmbDetailDTO.setIsUse(maEqCtgAsmbDetailDTO.getIsUse());
            maEqMstrAsmbDetailDTO.setRemark(maEqCtgAsmbDetailDTO.getRemark());
            
            result += maEqMstrAsmbDetailService.updateDetail(maEqMstrAsmbDetailDTO, maEqMstrCommonDTO, user);
        }
        
        return result;
    }

	@Override
	public int inputAsmbList(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrAsmbDetailDTO maEqMstrAsmbDetailDTO, User user) throws Exception
	{
	    MaEqCtgAsmbDetailService maEqCtgAsmbDetailService = (MaEqCtgAsmbDetailService) CommonUtil.getBean("maEqCtgAsmbDetailService", user);
	    
        int result = 0;
        
        String equipId = maEqMstrCommonDTO.getEquipId();
        String[] multiKey = maEqMstrAsmbDetailDTO.getMultiKey().split("\\+");
        
        for(int i=0; multiKey.length > i; i++)
        {
        	maEqMstrAsmbDetailDTO.setEqCtgAsmbId(multiKey[i]);
        	
        	MaEqCtgAsmbListDTO maEqCtgAsmbListDTO = new MaEqCtgAsmbListDTO();
        	maEqCtgAsmbListDTO.setEqCtgAsmbId(multiKey[i]);
        	MaEqCtgAsmbDetailDTO maEqCtgAsmbDetailDTO = maEqCtgAsmbDetailService.findDetail(maEqCtgAsmbListDTO, user);
        	
        	result += this.inputAsmb(maEqCtgAsmbDetailDTO, equipId, user);
        }
        
        return result;
	}
	
	@Override
    public int inputAsmbList(String eqCtgId, String equipId, User user) throws Exception
    {
	    MaEqCtgAsmbListService maEqCtgAsmbListService = (MaEqCtgAsmbListService) CommonUtil.getBean("maEqCtgAsmbListService", user);
	    
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
	    
	    // 유사설비공통부위여부 N
	    this.unlinkOldEqAsmb(equipId, user);
        // 변경된 설비종류 표준 부위 추가
	    MaEqCatalogCommonDTO maEqCatalogCommonDTO = new MaEqCatalogCommonDTO();
	    MaEqCtgAsmbListDTO maEqCtgAsmbListDTO = new MaEqCtgAsmbListDTO();
	    maEqCatalogCommonDTO.setEqCtgId(eqCtgId);
	    List<Map> eqCtgAsmbList = maEqCtgAsmbListService.findAsmbList(maEqCatalogCommonDTO, maEqCtgAsmbListDTO, user);
	    
	    for(Map eqCtgAsmb:eqCtgAsmbList){
	        MaEqCtgAsmbDetailDTO maEqCtgAsmbDetailDTO = (MaEqCtgAsmbDetailDTO) CommonUtil.makeDTO(eqCtgAsmb, MaEqCtgAsmbDetailDTO.class);
	        
	        if("Y".equals(maEqCtgAsmbDetailDTO.getIsEqTypeAsmb())) {
	            result += this.inputAsmb(maEqCtgAsmbDetailDTO, equipId, user);
	        }
	    }
	    
        return result;
    }
	
	@Override
	public String getEqAsmb(String equipId, String eqCtgAsmbId, User user) throws Exception
	{
	    String eqAsmbId = "";
	    
	    //validation
	    try{
	        if(Integer.parseInt(equipId)<=0 || Integer.parseInt(eqCtgAsmbId)<=0) {
	            throw new NumberFormatException();
	        }
	    }catch(NumberFormatException nfe){
	        nfe.printStackTrace();
	        return eqAsmbId;
	    }
	    
        MaEqMstrCommonDTO maEqMstrCommonDTO = new MaEqMstrCommonDTO();
        maEqMstrCommonDTO.setEquipId(equipId);
        MaEqMstrAsmbListDTO maEqMstrAsmbListDTO = new MaEqMstrAsmbListDTO();
        maEqMstrAsmbListDTO.setEqCtgAsmbId(eqCtgAsmbId);
        List<Map> eqAsmbList = maEqMstrAsmbListDAO.findAsmbList(maEqMstrCommonDTO, maEqMstrAsmbListDTO, user);
        if(eqAsmbList.size()>0) eqAsmbId = StringUtil.valueOf(eqAsmbList.get(0).get("EQASMBID"));
        
        return eqAsmbId;
	}
	
	@Override
	public int unlinkOldEqAsmb(String equipId, User user) throws Exception
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
        List<Map> eqAsmbList = maEqMstrAsmbListDAO.findAsmbList(maEqMstrCommonDTO, new MaEqMstrAsmbListDTO(), user);
        
        for(Map eqAsmb:eqAsmbList){
            MaEqMstrAsmbDetailDTO maEqMstrAsmbDetailDTO = (MaEqMstrAsmbDetailDTO) CommonUtil.makeDTO(eqAsmb, MaEqMstrAsmbDetailDTO.class);
            maEqMstrAsmbDetailDTO.setIsEqTypeAsmb("N");
            result += maEqMstrAsmbDetailService.updateDetail(maEqMstrAsmbDetailDTO, maEqMstrCommonDTO, user);
        }
	    
	    return result;
	}
	
	@Override
	public int undoEqCtgAsmb(String eqCtgAsmbId, User user) throws Exception
	{
	    int result = 0;
	    
	    //validation
        try{
            if(Integer.parseInt(eqCtgAsmbId)<=0) {
                throw new NumberFormatException();
            }
        }catch(NumberFormatException nfe){
            nfe.printStackTrace();
            return result;
        }
        
	    MaEqMstrCommonDTO maEqMstrCommonDTO = new MaEqMstrCommonDTO();
	    MaEqMstrAsmbListDTO maEqMstrAsmbListDTO = new MaEqMstrAsmbListDTO();
	    maEqMstrAsmbListDTO.setEqCtgAsmbId(eqCtgAsmbId);
        List<Map> eqAsmbList = maEqMstrAsmbListDAO.findAsmbList(maEqMstrCommonDTO, maEqMstrAsmbListDTO, user);
        
        for(Map eqAsmb:eqAsmbList){
            MaEqMstrAsmbDetailDTO maEqMstrAsmbDetailDTO = (MaEqMstrAsmbDetailDTO) CommonUtil.makeDTO(eqAsmb, MaEqMstrAsmbDetailDTO.class);
            maEqMstrAsmbDetailDTO.setIsEqTypeAsmb("N");
            result += maEqMstrAsmbDetailService.updateDetail(maEqMstrAsmbDetailDTO, maEqMstrCommonDTO, user);
        }
        
        return result;
	}

	@Override
	public void saveList(List<Map> gridList, User user) throws Exception
	{
		MaEqMstrCommonDTO maEqMstrCommonDTO = null;
		MaEqMstrAsmbDetailDTO maEqMstrAsmbDetailDTO = null;

		for(Map map : gridList)
        {
			maEqMstrCommonDTO = (MaEqMstrCommonDTO)CommonUtil.makeDTO(map, MaEqMstrCommonDTO.class);
			maEqMstrAsmbDetailDTO = (MaEqMstrAsmbDetailDTO)CommonUtil.makeDTO(map, MaEqMstrAsmbDetailDTO.class);
        	
			maEqMstrAsmbDetailDTO.setSetupDate(maEqMstrAsmbDetailDTO.getSetupDate());
        	maEqMstrCommonDTO.setCompNo(user.getCompNo());
        	
        	switch(maEqMstrAsmbDetailDTO.getNativeeditor())
        	{
        		case "inserted":
        			break;
        		case "updated" : maEqMstrAsmbDetailService.updateDetail(maEqMstrAsmbDetailDTO,maEqMstrCommonDTO, user);
        			break;
        		case "deleted": 
        			break;
        	}
        }
	}
}

