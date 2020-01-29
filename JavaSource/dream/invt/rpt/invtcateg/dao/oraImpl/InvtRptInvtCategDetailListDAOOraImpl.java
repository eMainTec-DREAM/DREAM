package dream.invt.rpt.invtcateg.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.invt.rpt.invtcateg.dao.InvtRptInvtCategDetailListDAO;
import dream.invt.rpt.invtcateg.dto.InvtRptInvtCategCommonDTO;
import dream.invt.rpt.invtcateg.dto.InvtRptInvtCategDetailListDTO;
import dream.invt.rpt.invtcateg.form.InvtRptInvtCategDetailListForm;

/**
 * 투자구분분석 상세 목록 dao
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="invtRptInvtCategDetailListDAOTarget"
 * @spring.txbn id="invtRptInvtCategDetailListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class InvtRptInvtCategDetailListDAOOraImpl extends BaseJdbcDaoSupportOra implements InvtRptInvtCategDetailListDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param invtRptInvtCategListForm
     * @param loginUser
     * @return List
     */
    public List findDetailList(InvtRptInvtCategDetailListForm invtRptInvtCategDetailListForm, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT							");
        query.append("    x.invt_categ 		INVTCATEGID	");
        query.append("    , SFACODE_TO_DESC(x.invt_categ,'INVT_CATEG', 'SYS', x.comp_no, '"+loginUser.getLangId()+"') INVTCATEGDESC		");
        query.append("    , x.invt_type 	INVTTYPEID	");
        query.append("    , SFACODE_TO_DESC(x.invt_type, 'INVT_TYPE', 'SYS', x.comp_no, '"+loginUser.getLangId()+"') INVTTYPEDESC		");
        query.append("    , COUNT(1) 		INVTTYPECNT ");
        query.append("FROM TAINVTLIST x					");
        query.append("WHERE 1=1							");
        query.append(this.getWhere(invtRptInvtCategDetailListForm,loginUser));
        query.append("GROUP BY x.comp_no, x.invt_type, x.invt_categ	");

        return getJdbcTemplate().queryForList(query.toString());
    }
    
    public String getWhere(InvtRptInvtCategDetailListForm invtRptInvtCategDetailListForm,User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        InvtRptInvtCategDetailListDTO invtRptInvtCategDetailListDTO = invtRptInvtCategDetailListForm.getInvtRptInvtCategDetailListDTO();

        query.getAndQuery("x.comp_no", loginUser.getCompNo());
        query.getAndQuery("x.invtlist_status", "C");
        query.append("AND EXISTS (SELECT 1 FROM TACDSYSD a 				");
        query.append("             WHERE a.list_type = 'INVT_CATEG' 	");
        query.append("               AND a.cdsysd_no = x.invt_categ)	");
        query.append("AND EXISTS (SELECT 1 FROM TACDSYSD a 				");
        query.append("             WHERE a.list_type = 'INVT_TYPE' 		");
        query.append("               AND a.cdsysd_no = x.invt_type)		");
        query.getAndQuery("x.invt_categ", invtRptInvtCategDetailListDTO.getInvtCategId());
        
        //사용부서
        query.getDeptLevelQuery("x.usage_dept", invtRptInvtCategDetailListDTO.getUsageDeptId(), invtRptInvtCategDetailListDTO.getUsageDeptDesc(), loginUser.getCompNo());

        //공장명
        query.getCodeLikeQuery("x.plant", "(SELECT a.description FROM  TAPLANT a WHERE a.comp_no = '"+loginUser.getCompNo()+"' AND a.plant = x.plant )", 
        		invtRptInvtCategDetailListDTO.getPlantId(), invtRptInvtCategDetailListDTO.getPlantDesc());
        
        //투자완료일자
        String fromDate = invtRptInvtCategDetailListDTO.getFromDate().replaceAll("-", "");
        String toDate   = invtRptInvtCategDetailListDTO.getToDate().replaceAll("-", "");
        
        if (fromDate != null && !"".equals(fromDate) && !"null".equals(fromDate))
        {
            query.append("AND x.end_date >= '"+fromDate+"'     ");
        }
        
        if (toDate != null && !"".equals(toDate) && !"null".equals(toDate))
        {
            query.append("AND x.end_date <= '"+toDate+"'       ");
        }
        
        return query.toString();
    }
    
}