package dream.req.work.service.spring;

import java.util.List;

import common.bean.User;
import common.util.DateUtil;
import dream.req.work.dao.ReqWorkResListDAO;
import dream.req.work.dto.ReqWorkCommonDTO;
import dream.req.work.dto.ReqWorkResDetailDTO;
import dream.req.work.dto.ReqWorkResListDTO;
import dream.req.work.service.ReqWorkResDetailService;
import dream.req.work.service.ReqWorkResListService;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultMstrDetailDTO;
import dream.work.list.service.MaWoResultMstrDetailService;

/**
 * 목록 serviceimpl
 * @author kim21017
 * @version $Id:$
 * @since 1.0
 *
 * @spring.bean id="reqWorkResListServiceTarget"
 * @spring.txbn id="reqWorkResListService"
 * @spring.property name="reqWorkResListDAO" ref="reqWorkResListDAO"
 * @spring.property name="maWoResultMstrDetailService" ref="maWoResultMstrDetailService"
 * @spring.property name="reqWorkResDetailService" ref="reqWorkResDetailService"
 */
public class ReqWorkResListServiceImpl implements ReqWorkResListService
{
    private ReqWorkResListDAO reqWorkResListDAO = null;

    private MaWoResultMstrDetailService maWoResultMstrDetailService = null;

    private ReqWorkResDetailService reqWorkResDetailService = null;


	public ReqWorkResDetailService getReqWorkResDetailService() {
		return reqWorkResDetailService;
	}

	public void setReqWorkResDetailService(ReqWorkResDetailService reqWorkResDetailService) {
		this.reqWorkResDetailService = reqWorkResDetailService;
	}

	public MaWoResultMstrDetailService getMaWoResultMstrDetailService() {
		return maWoResultMstrDetailService;
	}

	public void setMaWoResultMstrDetailService(MaWoResultMstrDetailService maWoResultMstrDetailService) {
		this.maWoResultMstrDetailService = maWoResultMstrDetailService;
	}

	public ReqWorkResListDAO getReqWorkResListDAO() {
		return reqWorkResListDAO;
	}

	public void setReqWorkResListDAO(ReqWorkResListDAO reqWorkResListDAO) {
		this.reqWorkResListDAO = reqWorkResListDAO;
	}

	public List findList(ReqWorkCommonDTO reqWorkCommonDTO,ReqWorkResListDTO reqWorkResListDTO, User user)
    {
        return reqWorkResListDAO.findList(reqWorkCommonDTO,reqWorkResListDTO,user);
    }

    public int deleteList(String[] deleteRows, String compNo)
    {
        int result = 0;
        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + reqWorkResListDAO.deleteList(id,compNo);
            }
        return result;
    }

	@Override
	public void linkWo(ReqWorkCommonDTO reqWorkCommonDTO, User user) throws Exception {

    	MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = new MaWoResultMstrCommonDTO();
    	maWoResultMstrCommonDTO.setWkOrId(reqWorkCommonDTO.getWkorId());
    	maWoResultMstrCommonDTO.setCompNo(user.getCompNo());
    	maWoResultMstrCommonDTO.setUserLang(user.getLangId());
        // 조회된 상세 부분
        MaWoResultMstrDetailDTO maWoResultMstrDetailDTO = maWoResultMstrDetailService.findDetail(maWoResultMstrCommonDTO);

    	ReqWorkResDetailDTO reqWorkResDetailDTO = new ReqWorkResDetailDTO();
    	reqWorkResDetailDTO.setWoReqResId(reqWorkResListDAO.getNextSequence("SQAWOREQRES_ID"));
    	reqWorkResDetailDTO.setResDate(DateUtil.getDateTime("yyyy-MM-dd"));
    	reqWorkResDetailDTO.setResStatusId("WRK"); //작업중
    	reqWorkResDetailDTO.setDeptId(maWoResultMstrDetailDTO.getDeptId());
    	reqWorkResDetailDTO.setEmpId(maWoResultMstrDetailDTO.getEmpId());
    	reqWorkResDetailDTO.setResponse(maWoResultMstrDetailDTO.getWkOrDesc());
    	reqWorkResDetailDTO.setWkorId(maWoResultMstrDetailDTO.getWkOrId());

    	reqWorkCommonDTO.setCompNo(maWoResultMstrCommonDTO.getCompNo());

    	reqWorkResDetailService.insertDetail(reqWorkResDetailDTO, reqWorkCommonDTO);
	}

	@Override
	public String findTotalCount(ReqWorkCommonDTO reqWorkCommonDTO, ReqWorkResListDTO reqWorkResListDTO, User user)
			throws Exception {
		return reqWorkResListDAO.findTotalCount(reqWorkCommonDTO, reqWorkResListDTO, user);
	}


}