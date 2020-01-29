package dream.req.work.service.spring;

import java.util.List;

import common.bean.User;
import dream.req.work.dao.ReqWoPlanRsltListDAO;
import dream.req.work.dto.MaWoReqCommonDTO;
import dream.req.work.dto.MaWoReqResDetailDTO;
import dream.req.work.dto.ReqWoPlanRsltListDTO;
import dream.req.work.service.MaWoReqResDetailService;
import dream.req.work.service.ReqWoPlanRsltListService;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultMstrDetailDTO;
import dream.work.list.service.MaWoResultMstrDetailService;

/**
 * ��� serviceimpl
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="reqWoPlanRsltListServiceTarget"
 * @spring.txbn id="reqWoPlanRsltListService"
 * @spring.property name="reqWoPlanRsltListDAO" ref="reqWoPlanRsltListDAO"
 * @spring.property name="maWoResultMstrDetailService" ref="maWoResultMstrDetailService"
 * @spring.property name="maWoReqResDetailService" ref="maWoReqResDetailService"
 */
public class ReqWoPlanRsltListServiceImpl implements ReqWoPlanRsltListService
{
    private ReqWoPlanRsltListDAO reqWoPlanRsltListDAO						= null;
    
    private MaWoResultMstrDetailService maWoResultMstrDetailService = null;
    
    private MaWoReqResDetailService maWoReqResDetailService 		= null;
    

	public MaWoReqResDetailService getMaWoReqResDetailService() {
		return maWoReqResDetailService;
	}

	public void setMaWoReqResDetailService(MaWoReqResDetailService maWoReqResDetailService) {
		this.maWoReqResDetailService = maWoReqResDetailService;
	}

	public MaWoResultMstrDetailService getMaWoResultMstrDetailService() {
		return maWoResultMstrDetailService;
	}

	public void setMaWoResultMstrDetailService(MaWoResultMstrDetailService maWoResultMstrDetailService) {
		this.maWoResultMstrDetailService = maWoResultMstrDetailService;
	}

	public ReqWoPlanRsltListDAO getReqWoPlanRsltListDAO() {
		return reqWoPlanRsltListDAO;
	}

	public void setReqWoPlanRsltListDAO(ReqWoPlanRsltListDAO reqWoPlanRsltListDAO) {
		this.reqWoPlanRsltListDAO = reqWoPlanRsltListDAO;
	}

	public List findList(MaWoReqCommonDTO maWoReqCommonDTO,ReqWoPlanRsltListDTO reqWoPlanRsltListDTO, User user)
    {      
        return reqWoPlanRsltListDAO.findList(maWoReqCommonDTO,reqWoPlanRsltListDTO,user);
    }

    public int deleteList(String[] deleteRows, String compNo)
    {
        int result = 0;
        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + reqWoPlanRsltListDAO.deleteList(id,compNo);
            }
        return result;
    }

	@Override
	public void linkWo(MaWoReqCommonDTO maWoReqCommonDTO, ReqWoPlanRsltListDTO reqWoPlanRsltListDTO, User user) throws Exception {

    	MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = new MaWoResultMstrCommonDTO();
    	maWoResultMstrCommonDTO.setWkOrId(maWoReqCommonDTO.getWkorId());
    	maWoResultMstrCommonDTO.setCompNo(user.getCompNo());
    	maWoResultMstrCommonDTO.setUserLang(user.getLangId());
        // ��ȸ�� �� �κ�
        MaWoResultMstrDetailDTO maWoResultMstrDetailDTO = maWoResultMstrDetailService.findDetail(maWoResultMstrCommonDTO);
      
        MaWoReqResDetailDTO maWoReqResDetailDTO = new MaWoReqResDetailDTO();
    	maWoReqResDetailDTO.setWoReqResId(reqWoPlanRsltListDAO.getNextSequence("SQAWOREQRES_ID"));
    	maWoReqResDetailDTO.setResDate(maWoResultMstrDetailDTO.getWkorDate()); // �۾����ڷ� �����ؾ� ��.
    	maWoReqResDetailDTO.setWoReqGenType("EM"); //���Ŀ���ó��..

    	if("C".equals(maWoResultMstrDetailDTO.getWoStatusId())||"PRC".equals(maWoResultMstrDetailDTO.getWoStatusId()))
    	{
    		maWoReqResDetailDTO.setResStatusId("COM"); //�۾���
    	}
    	else
    	{
    		maWoReqResDetailDTO.setResStatusId("WRK"); //�۾���
    	}
    	
    	maWoReqResDetailDTO.setDeptId(maWoResultMstrDetailDTO.getDeptId());
    	maWoReqResDetailDTO.setEmpId(maWoResultMstrDetailDTO.getEmpId());
    	maWoReqResDetailDTO.setResponse(maWoResultMstrDetailDTO.getWkOrDesc());
    	maWoReqResDetailDTO.setWkorId(maWoResultMstrDetailDTO.getWkOrId());

    	maWoResultMstrDetailDTO.setCompNo(user.getCompNo());
    	
    	maWoResultMstrDetailService.insertDetail(maWoResultMstrDetailDTO, maWoResultMstrCommonDTO, user);
	}
	
	@Override
	public String findTotalCount(MaWoReqCommonDTO maWoReqCommonDTO, ReqWoPlanRsltListDTO reqWoPlanRsltListDTO, User user)
			throws Exception {
		return reqWoPlanRsltListDAO.findTotalCount(maWoReqCommonDTO, reqWoPlanRsltListDTO, user);
	}

}