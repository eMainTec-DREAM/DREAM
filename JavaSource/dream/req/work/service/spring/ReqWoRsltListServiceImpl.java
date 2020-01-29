package dream.req.work.service.spring;

import java.util.List;

import common.bean.User;
import common.util.CommonUtil;
import dream.req.work.dao.ReqWoRsltListDAO;
import dream.req.work.dto.MaWoReqCommonDTO;
import dream.req.work.dto.MaWoReqDetailDTO;
import dream.req.work.dto.MaWoReqResDetailDTO;
import dream.req.work.dto.ReqWoRsltListDTO;
import dream.req.work.service.MaWoReqResDetailService;
import dream.req.work.service.ReqWoRsltListService;
import dream.work.list.dto.MaWoResultFailDetailDTO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultMstrDetailDTO;
import dream.work.list.service.MaWoResultMstrDetailService;

/**
 * 목록 serviceimpl
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="reqWoRsltListServiceTarget"
 * @spring.txbn id="reqWoRsltListService"
 * @spring.property name="reqWoRsltListDAO" ref="reqWoRsltListDAO"
 * @spring.property name="maWoResultMstrDetailService" ref="maWoResultMstrDetailService"
 * @spring.property name="maWoReqResDetailService" ref="maWoReqResDetailService"
 */
public class ReqWoRsltListServiceImpl implements ReqWoRsltListService
{
    private ReqWoRsltListDAO reqWoRsltListDAO                       = null;
    
    private MaWoResultMstrDetailService maWoResultMstrDetailService = null;
    
    private MaWoReqResDetailService maWoReqResDetailService         = null;
    
    
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

    public ReqWoRsltListDAO getReqWoRsltListDAO() {
        return reqWoRsltListDAO;
    }

    public void setReqWoRsltListDAO(ReqWoRsltListDAO reqWoRsltListDAO) {
        this.reqWoRsltListDAO = reqWoRsltListDAO;
    }

    public List findList(MaWoReqCommonDTO maWoReqCommonDTO,ReqWoRsltListDTO reqWoRsltListDTO, User user)
    {      
        return reqWoRsltListDAO.findList(maWoReqCommonDTO,reqWoRsltListDTO,user);
    }

    public int deleteList(String[] deleteRows, User user)
    {
        int result = 0;
        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + reqWoRsltListDAO.deleteList(id, user.getCompNo());
            }
        return result;
    }

    @Override
    public void linkWo(MaWoReqCommonDTO maWoReqCommonDTO, MaWoReqDetailDTO maWoReqDetailDTO, User user) throws Exception {
        maWoReqResDetailService = (MaWoReqResDetailService) CommonUtil.getBean("maWoReqResDetailService", user);
        // Service 객체 생성
        MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = new MaWoResultMstrCommonDTO();
        maWoResultMstrCommonDTO.setWkOrId(maWoReqCommonDTO.getWkorId());
        maWoResultMstrCommonDTO.setCompNo(user.getCompNo());
        maWoResultMstrCommonDTO.setUserLang(user.getLangId());
        // 조회된 상세 부분
        MaWoResultMstrDetailDTO maWoResultMstrDetailDTO = maWoResultMstrDetailService.findDetail(maWoResultMstrCommonDTO);
      
        MaWoReqResDetailDTO maWoReqResDetailDTO = new MaWoReqResDetailDTO();
        maWoReqResDetailDTO.setWoReqResId(reqWoRsltListDAO.getNextSequence("SQAWOREQRES_ID"));
        maWoReqResDetailDTO.setResDate(maWoResultMstrDetailDTO.getWkorDate()); // 작업일자로 변경해야 함.
        maWoReqResDetailDTO.setWoreqresMethod("WO");
        maWoReqResDetailDTO.setWoReqGenType("EM"); //사후연결처리..
        
        if("C".equals(maWoResultMstrDetailDTO.getWoStatusId())||"PRC".equals(maWoResultMstrDetailDTO.getWoStatusId()))
        {
            maWoReqResDetailDTO.setResStatusId("COM"); //작업중
        }
        else
        {
            maWoReqResDetailDTO.setResStatusId("WRK"); //작업중
        }
        
        maWoReqResDetailDTO.setDeptId(maWoResultMstrDetailDTO.getDeptId());
        maWoReqResDetailDTO.setEmpId(maWoResultMstrDetailDTO.getEmpId());
        maWoReqResDetailDTO.setResponse(maWoResultMstrDetailDTO.getWkOrDesc());
        maWoReqResDetailDTO.setWkorId(maWoResultMstrDetailDTO.getWkOrId());
        
        maWoReqCommonDTO.setCompNo(maWoResultMstrCommonDTO.getCompNo());
        
        maWoReqResDetailService.insertDetail(maWoReqResDetailDTO, maWoReqCommonDTO);
        
        // 기존작업 선택 시 작업종류가 고장인 경우 작업결과(tawofail)에 값이 비어 있으면 taworeq를 넣어줌
        if("BM".equals(maWoResultMstrDetailDTO.getWoTypeId()))
        {
        	if(!"C".equals(maWoResultMstrDetailDTO.getWoStatusId())&&!"PRC".equals(maWoResultMstrDetailDTO.getWoStatusId()))
            {
	        	//작업결과(tawofail)값이 있는지 조회
	        	MaWoResultFailDetailDTO maWoResultFailDetailDTO = maWoResultMstrDetailService.findFailDetail(maWoResultMstrCommonDTO);

	        	//작업결과(tawofail) 설비고장시작시간 값이 있는지 조회
	        	if("".equals(maWoResultFailDetailDTO.getEqDnStartDate()) || null == maWoResultFailDetailDTO.getEqDnStartDate())
	        	{
	        		//작업결과(tawofail)에 값이 비어 있으면 taworeq를 넣어 줌
	        		maWoResultFailDetailDTO.setEqDnStartDate(maWoReqDetailDTO.getEqDnDate());
	        		maWoResultFailDetailDTO.setEqDnStartTime(maWoReqDetailDTO.getEqDnTime());
	        		
	        		maWoResultMstrDetailService.updateFailDetail(maWoResultMstrCommonDTO, maWoResultFailDetailDTO, user);
	        		
	        	}
            }
        }
    }
    
    @Override
    public String findTotalCount(MaWoReqCommonDTO maWoReqCommonDTO, ReqWoRsltListDTO reqWoRsltListDTO, User user)
            throws Exception {
        return reqWoRsltListDAO.findTotalCount(maWoReqCommonDTO, reqWoRsltListDTO, user);
    }

}