package dream.req.work.service.spring;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.fins.gt.util.StringUtils;

import common.bean.ResponseDTO;
import common.bean.User;
import common.util.MessageUtil;
import common.util.StringUtil;
import dream.req.work.dao.ReqWorkListDAO;
import dream.req.work.dto.ReqWorkCommonDTO;
import dream.req.work.service.ReqWorkListService;

/**
 * ¸ñ·Ï serviceimpl
 * @author kim21017
 * @version $Id:$
 * @since 1.0
 *
 * @spring.bean id="reqWorkListServiceTarget"
 * @spring.txbn id="reqWorkListService"
 * @spring.property name="reqWorkListDAO" ref="reqWorkListDAO"
 */
public class ReqWorkListServiceImpl implements ReqWorkListService
{
    private ReqWorkListDAO reqWorkListDAO = null;

    public ReqWorkListDAO getReqWorkListDAO()
    {
        return reqWorkListDAO;
    }

    public void setReqWorkListDAO(ReqWorkListDAO reqWorkListDAO)
    {
        this.reqWorkListDAO = reqWorkListDAO;
    }

    public List findList(ReqWorkCommonDTO reqWorkCommonDTO, User user)
    {
        return reqWorkListDAO.findList(reqWorkCommonDTO,user);
    }

    public ResponseDTO deleteList(String[] deleteRows, User user)
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
        ReqWorkCommonDTO reqWorkCommonDTO = new ReqWorkCommonDTO();
        reqWorkCommonDTO.setWoReqId(StringUtils.join(deleteRows,"+"));
        List<Map> list = this.findList(reqWorkCommonDTO, user);
        for(Map map:list)
        {
            if(!"WRT".equals(StringUtil.valueOf(map.get("WOREQSTATUS")))) {
                isDeletable = false;
                builder.addData(map);
            }
        }
        if(!isDeletable) {
            builder
            .status(HttpServletResponse.SC_BAD_REQUEST)
            .message(MessageUtil.getMessage(user.getLocale(), "MESSAGE", "MSG0153"));
            return builder.build();
        }
        
        for(Map map:list)
        {
            reqWorkListDAO.deleteList(StringUtil.valueOf(map.get("WOREQID")),user.getCompNo());
            reqWorkListDAO.deleteResList(StringUtil.valueOf(map.get("WOREQID")),user.getCompNo());
        }
        builder
        .status(HttpServletResponse.SC_OK)
        .message(MessageUtil.getMessage(user.getLocale(), "MESSAGE", "MSG021"))
        .data(list);
        
        return builder.build();
    }

	@Override
	public String findTotalCount(ReqWorkCommonDTO reqWorkCommonDTO, User user) throws Exception {
		return reqWorkListDAO.findTotalCount(reqWorkCommonDTO, user);
	}


}