package dream.part.pur.req.service.spring;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.quartz.Trigger;

import common.bean.MwareConfig;
import common.bean.User;
import common.util.CommonUtil;
import common.util.QueryBuffer;
import dream.mgr.message.dto.MgrMessageTransDetailDTO;
import dream.mgr.message.service.MgrMessageTransDetailService;
import dream.part.pur.req.dao.MaPtPurReqListDAO;
import dream.part.pur.req.dto.MaPtPurReqDetailDTO;
import dream.part.pur.req.dto.MaPtReqCommonDTO;
import dream.part.pur.req.service.MaPtPurReqDetailService;
import dream.part.pur.req.service.MaPtPurReqListService;
import dream.scheduler.autoschedule.service.MaBatchService;

/**
 * 부품수리 - 목록 serviceimpl
 * @author ssong
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="maPtPurReqListServiceTarget"
 * @spring.txbn id="maPtPurReqListService"
 * @spring.property name="maPtPurReqListDAO" ref="maPtPurReqListDAO"
 * @spring.property name="maPtPurReqDetailService" ref="maPtPurReqDetailService"
 * @spring.property name="mgrMessageTransDetailService" ref="mgrMessageTransDetailService"
 */
public class MaPtPurReqListServiceImpl implements MaPtPurReqListService
{
    private MaPtPurReqListDAO maPtPurReqListDAO = null;
    private MaPtPurReqDetailService maPtPurReqDetailService = null;
    private MgrMessageTransDetailService mgrMessageTransDetailService = null;
    private MaBatchService maBatchService = null;
    
    public MaBatchService getMaBatchService()
    {
        return maBatchService;
    }

    public void setMaBatchService(MaBatchService maBatchService)
    {
        this.maBatchService = maBatchService;
    }

    public MgrMessageTransDetailService getMgrMessageTransDetailService()
    {
        return mgrMessageTransDetailService;
    }

    public void setMgrMessageTransDetailService(
            MgrMessageTransDetailService mgrMessageTransDetailService)
    {
        this.mgrMessageTransDetailService = mgrMessageTransDetailService;
    }

    public MaPtPurReqDetailService getMaPtPurReqDetailService()
    {
        return maPtPurReqDetailService;
    }

    public void setMaPtPurReqDetailService(
            MaPtPurReqDetailService maPtPurReqDetailService)
    {
        this.maPtPurReqDetailService = maPtPurReqDetailService;
    }

    public MaPtPurReqListDAO getMaPtPurReqListDAO() 
    {
        return maPtPurReqListDAO;
    }

    public void setMaPtPurReqListDAO(MaPtPurReqListDAO maPtPurReqListDAO) 
    {
        this.maPtPurReqListDAO = maPtPurReqListDAO;
    }
    public List findList(MaPtReqCommonDTO maPtReqCommonDTO, User user)
    {      
        return maPtPurReqListDAO.findList(maPtReqCommonDTO,user);
    }
    
    public void saveList(List<Map> gridList, User user) throws Exception
    {
        MaPtPurReqDetailDTO maPtPurReqDetailDTO = null;
                
        for (Map map : gridList) {
            maPtPurReqDetailDTO = (MaPtPurReqDetailDTO)CommonUtil.makeDTO(map, MaPtPurReqDetailDTO.class);
            switch (maPtPurReqDetailDTO.getNativeeditor())
            {
                case "inserted":
                    break;
                case "updated" : maPtPurReqDetailService.updateDetail(maPtPurReqDetailDTO, user);
                    break;
                case "deleted": maPtPurReqListDAO.deleteList(user, maPtPurReqDetailDTO.getReqId()); 
                    break;
            }
        }
    }
    
    public int deleteList(User user, String[] deleteRows) throws Exception
    {
        int result = 0;

        for(int i=0; i<deleteRows.length; i++) {
            String chkDelUser = maPtPurReqListDAO.chkDelUser(deleteRows[i], user);
            if(!"0".equals(chkDelUser)) {
                result = result + maPtPurReqListDAO.deleteList(user, deleteRows[i]);   
            }
        }
        return result;
    }

    @Override
    public int updateStatus(final User user, final String[] updateRows) throws Exception
    {
        int result = 0;
        for(int i=0; i<updateRows.length; i++) {
            final String updateRow = updateRows[i];
            String chkPurStatus = maPtPurReqListDAO.chkPurStatus(updateRow, user);
  
            if(!"0".equals(chkPurStatus)) {
               
               result += maPtPurReqListDAO.updateStatus(user, updateRow);
            }
            
            if("Y".equals(MwareConfig.getIsUseMailService())) {
                Runnable myThreadTask = new Runnable() {
                    public void run() {
                        try {
                            String[] receiveMailArr = new String[0];
                            String[] receiveEmpNoArr = new String[0];
                            receiveMailArr = maPtPurReqListDAO.findMaPtPurReqUserMailList(updateRow, user);
                            receiveEmpNoArr = maPtPurReqListDAO.findMaPtPurReqUserEmpNoList(updateRow, user);
                            if(receiveMailArr.length <= 0) {
                                receiveMailArr = maPtPurReqListDAO.findMaPtPurReqDeptMailList(updateRow, user);
                                receiveEmpNoArr = maPtPurReqListDAO.findMaPtPurReqDeptEmpNoList(updateRow, user);
                            } else if(receiveMailArr.length > 0) {
                                String title = maPtPurReqListDAO.findTitle("MSG260", user);
                                String contents = setMailFormatWoReqDetail(maPtPurReqListDAO.selectMaPtPurReqDetail(updateRow, user));

                                String sendEmpNo = user.getEmpNo();
                                String recEmpNo = "";
                                String msgObjType = "PRI10";
                                String objId = updateRow;
                                String objNo = updateRow;
                                
                                sendMail(receiveMailArr, receiveEmpNoArr, contents, title, user
                                		,sendEmpNo ,recEmpNo, msgObjType, objId, objNo);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                Thread thread = new Thread(myThreadTask);
                thread.start();
            }
        }
        return result;
    }
    
    public String setMailFormatWoReqDetail(List detailInfo)
    {
        QueryBuffer contents = new QueryBuffer();
        
        contents.append("<html><head></head><table width='100%'><tr><td width='100%'>");
        
        for (int i=0;i<detailInfo.size(); i++){
            Map map = (Map) detailInfo.get(i);
            Set key = map.keySet();
            
            for(Iterator it = key.iterator(); it.hasNext();)
            {
                String keyName = (String)it.next();
                String valueName = String.valueOf(map.get(keyName));
                if("TITLE".equals(keyName)){
                    contents.append("<table width='100%' style='font-family:'맑은 고딕','Malgun Gothic','돋움',Dotum,Arial,Helvetica,sans-serif; font-size:12px; margin:10px auto; border-collapse:collapse; border:1px solid #ccc;'>");
                    contents.append("<caption style='padding:6px 10px; border-collapse:collapse; border:1px solid #ccc; font-size:20px; border:none; color:#000; font-weight:bold;'>");
                    contents.append(valueName);
                    contents.append("</caption>");
                    contents.append("<tbody>");
                    contents.append("<tr height='18'>");
                    contents.append("<td colspan='4'  align='right' style=' padding:6px 10px; border-collapse:collapse; border:0px solid #ccc;'>");
                    contents.append("<a target='_blank' style='font-size:12px;' href='\" + MwareConfig.getDreamUrl() + \"'>");
                    contents.append("Login To Dream");
                    contents.append("</a></td>");
                    contents.append("</tr>");
                } else if("PTPNLISTNO".equals(keyName)) {
                    contents.append("<tr height='22'>");
                    contents.append("<td height='22' align='center' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
                    contents.append("요청#");
                    contents.append("</td>");
                    contents.append("<td align='left' style=' padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
                    contents.append(valueName);
                    contents.append("</td>");
                } else if("PTPNLISTSTATUS".equals(keyName)) {
                    contents.append("<td height='22' align='center' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
                    contents.append("진행상태");
                    contents.append("</td>");
                    contents.append("<td align='left' style=' padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
                    contents.append(valueName);
                    contents.append("</td>");
                    contents.append("</tr>");
                } else if("PARTNO".equals(keyName)) {
                    contents.append("<tr height='22'>");
                    contents.append("<td height='22' align='center' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
                    contents.append("부품번호");
                    contents.append("</td>");
                    contents.append("<td align='left' style=' padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
                    contents.append(valueName);
                    contents.append("</td>");
                } else if("PARTDESC".equals(keyName)) {
                    contents.append("<td height='22' align='center' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
                    contents.append("부품명");
                    contents.append("</td>");
                    contents.append("<td align='left' style=' padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
                    contents.append(valueName);
                    contents.append("</td>");
                    contents.append("</tr>");
                } else if("RECQTY".equals(keyName)) {
                    contents.append("<tr height='22'>");
                    contents.append("<td height='22' align='center' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
                    contents.append("수&nbsp;&nbsp;&nbsp;&nbsp;량");
                    contents.append("</td>");
                    contents.append("<td align='left' style=' padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
                    contents.append(valueName);
                    contents.append("</td>");
                } else if("PTSIZE".equals(keyName)) {
                    contents.append("<td height='22' align='center' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
                    contents.append("규&nbsp;&nbsp;&nbsp;격");
                    contents.append("</td>");
                    contents.append("<td align='left' style=' padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
                    contents.append(valueName);
                    contents.append("</td>");
                    contents.append("</tr>");
                } else if("DEPTDESC".equals(keyName)) {
                    contents.append("<tr height='22'>");
                    contents.append("<td height='22' align='center' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
                    contents.append("신청부서");
                    contents.append("</td>");
                    contents.append("<td align='left' style=' padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
                    contents.append(valueName);
                    contents.append("</td>");
                } else if("EMPNAME".equals(keyName)) {
                    contents.append("<td height='22' align='center' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
                    contents.append("신청자");
                    contents.append("</td>");
                    contents.append("<td align='left' style=' padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
                    contents.append(valueName);
                    contents.append("</td>");
                    contents.append("</tr>");
                } else if("REQDATE".equals(keyName)) {
                    contents.append("<tr height='22'>");
                    contents.append("<td height='22' align='center' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
                    contents.append("신청일자");
                    contents.append("</td>");
                    contents.append("<td align='left' style=' padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
                    contents.append(valueName);
                    contents.append("</td>");
                } else if("PLANTDESC".equals(keyName)) {
                    contents.append("<td height='22' align='center' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
                    contents.append("공&nbsp;&nbsp;&nbsp;장");
                    contents.append("</td>");
                    contents.append("<td align='left' style=' padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
                    contents.append(valueName);
                    contents.append("</td>");
                    contents.append("</tr>");
                } else if("EQUIPDESC".equals(keyName)) {
                    contents.append("<tr height='22'>");
                    contents.append("<td height='22' align='center' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
                    contents.append("설&nbsp;&nbsp;&nbsp;&nbsp;비");
                    contents.append("</td>");
                    contents.append("<td colspan='3' align='left' style=' padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
                    contents.append(valueName);
                    contents.append("</td>");
                    contents.append("</tr>");
                } else if("USAGE".equals(keyName)) {
                    contents.append("<tr height='22'>");
                    contents.append("<td height='22' align='center' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
                    contents.append("용&nbsp;&nbsp;&nbsp;&nbsp;도");
                    contents.append("</td>");
                    contents.append("<td colspan='3' align='left' style=' padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
                    contents.append(valueName);
                    contents.append("</td>");
                    contents.append("</tr>");
                } else if("REMARK".equals(keyName)) {
                    contents.append("<tr height='22'>");
                    contents.append("<td colspan='4'  height='22' align='center' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
                    contents.append("비고");
                    contents.append("</td>");
                    contents.append("</tr>");
                    contents.append("<tr height='200'>");
                    contents.append("<td colspan='4' valign='top'  align='left' style=' padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
                    contents.append(valueName);
                    contents.append("</td>");
                    contents.append("</tr>");
                }
            }
        }
        contents.append("<tbody>");
        contents.append("</TABLE>");
        contents.append("</td></tr>");
        contents.append("</TABLE>");
        contents.append("</html>");
        
        return contents.toString();
    }
    
    private int sendMail(String[] receiveMailArr, String[] receiveEmpNoArr, String contents, String title, User user
    		, String sendEmpNo, String recEmpNo, String msgObjType, String objectId,  String objectNo) throws Exception
    {
        int cnt = 0;
        MgrMessageTransDetailDTO mgrMessageTransDetailDTO = new MgrMessageTransDetailDTO();
        
        for(String receiveMail:receiveMailArr) {
            mgrMessageTransDetailDTO.setMessageId(maPtPurReqListDAO.getNextSequence("sqamessagelist_id"));
            mgrMessageTransDetailDTO.setDescription(title);
            mgrMessageTransDetailDTO.setContents(contents);
            mgrMessageTransDetailDTO.setReceiver(receiveMail);
            mgrMessageTransDetailDTO.setMethodTypeId("MAIL");
            mgrMessageTransDetailDTO.setMsgStatusId("Z");

            mgrMessageTransDetailDTO.setSendEmpNo(sendEmpNo);
            mgrMessageTransDetailDTO.setRecEmpNo(receiveEmpNoArr[cnt]);
            mgrMessageTransDetailDTO.setMsgObjType(msgObjType);
            mgrMessageTransDetailDTO.setObjectId(objectId);
            mgrMessageTransDetailDTO.setObjectNo(objectNo);
            
            cnt += mgrMessageTransDetailService.insertDetail(mgrMessageTransDetailDTO, user);
        }
        return cnt;
    }

	@Override
	public String findTotalCount(MaPtReqCommonDTO maPtReqCommonDTO, User user) throws Exception {
        return maPtPurReqListDAO.findTotalCount(maPtReqCommonDTO, user);
	}
}