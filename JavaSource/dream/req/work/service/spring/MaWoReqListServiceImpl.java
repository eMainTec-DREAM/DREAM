package dream.req.work.service.spring;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.fins.gt.util.StringUtils;

import common.bean.ResponseDTO;
import common.bean.User;
import common.util.CommonUtil;
import common.util.MessageUtil;
import common.util.StringUtil;
import dream.req.work.dao.MaWoReqListDAO;
import dream.req.work.dto.MaWoReqCommonDTO;
import dream.req.work.service.MaWoReqListService;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.service.MaWoResultMstrListService;

/**
 * 목록 serviceimpl
 * @author kim21017
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="maWoReqListServiceTarget"
 * @spring.txbn id="maWoReqListService"
 * @spring.property name="maWoReqListDAO" ref="maWoReqListDAO"
 */
public class MaWoReqListServiceImpl implements MaWoReqListService
{
    private MaWoReqListDAO maWoReqListDAO = null;

    public MaWoReqListDAO getMaWoReqListDAO() 
    {
        return maWoReqListDAO;
    }

    public void setMaWoReqListDAO(MaWoReqListDAO maWoReqListDAO) 
    {
        this.maWoReqListDAO = maWoReqListDAO;
    }

    public List findList(MaWoReqCommonDTO maWoReqCommonDTO, User user) throws Exception
    {   
        //WO 조건은 WO 서비스에서 조회
        if(!"".equals(maWoReqCommonDTO.getFilterWoNo())){
            MaWoResultMstrListService maWoResultMstrListService = (MaWoResultMstrListService) CommonUtil.getBean("maWoResultMstrListService", user);
            MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = new MaWoResultMstrCommonDTO();
            maWoResultMstrCommonDTO.setFilterWoNo(maWoReqCommonDTO.getFilterWoNo());
            String[] wkorIds = StringUtil.toSingleArray(maWoResultMstrListService.findWoResultMstrList(maWoResultMstrCommonDTO, user), "WKORID");
            String mergedWkorId = StringUtils.join(wkorIds,"+");
            //조건에 맞는 작업이 없으면 아무것도 조회하지 않아야 함
            if("".equals(mergedWkorId)) mergedWkorId = "-1";
            maWoReqCommonDTO.setWkorId(mergedWkorId);
            
        }
        return maWoReqListDAO.findList(maWoReqCommonDTO,user);
    }

    public ResponseDTO deleteList(String[] deleteRows, User user) throws Exception
    {
        ResponseDTO.Builder builder = new ResponseDTO.Builder(MessageUtil.getMessage(user.getLocale(), "MESSAGE", "MSG021"));
        
        //no selected rows
        if(deleteRows.equals(null) || deleteRows.length==0) {
            builder
            .status(HttpServletResponse.SC_BAD_REQUEST)
            .message(MessageUtil.getMessage(user.getLocale(), "MESSAGE", "MSG0074"));
            return builder.build();
        }
        
        //validation
        boolean isDeletable = true;
        MaWoReqCommonDTO maWoReqCommonDTO = new MaWoReqCommonDTO();
        maWoReqCommonDTO.setWoReqId(StringUtils.join(deleteRows,"+"));
        List<Map> list = this.findList(maWoReqCommonDTO, user);
        for(Map map:list)
        {
            if(!"REQ".equals(StringUtil.valueOf(map.get("WOREQSTATUS")))) {
                isDeletable = false;
                builder.addData(map);
            }
            else if(!user.getEmpId().equals(StringUtil.valueOf(map.get("REQEMPID")))) {
                isDeletable = false;
                builder.addData(map);
            }
        }
        if(!isDeletable) {
            builder
            .status(HttpServletResponse.SC_BAD_REQUEST)
            .message(MessageUtil.getMessage(user.getLocale(), "MESSAGE", "MSG1011"));
            return builder.build();
        }
        
        //success
        for(Map map:list)
        {
            maWoReqListDAO.deleteList(StringUtil.valueOf(map.get("WOREQID")),user.getCompNo());
            maWoReqListDAO.deleteResList(StringUtil.valueOf(map.get("WOREQID")),user.getCompNo());
        }
        builder
        .status(HttpServletResponse.SC_OK)
        .message(MessageUtil.getMessage(user.getLocale(), "MESSAGE", "MSG021"))
        .data(list);
        
        return builder.build();
    }

	@Override
	public String findTotalCount(MaWoReqCommonDTO maWoReqCommonDTO, User user) throws Exception {
		return maWoReqListDAO.findTotalCount(maWoReqCommonDTO, user);
	}


}