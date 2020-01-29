package dream.invt.rpt.invtcateg.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.invt.rpt.invtcateg.dao.InvtRptInvtCategListDAO;
import dream.invt.rpt.invtcateg.dto.InvtRptInvtCategCommonDTO;
import dream.invt.rpt.invtcateg.form.InvtRptInvtCategListForm;

/**
 * 투자구분분석 목록 dao
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="invtRptInvtCategListDAOTarget"
 * @spring.txbn id="invtRptInvtCategListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class InvtRptInvtCategListDAOSqlImpl extends BaseJdbcDaoSupportSql implements InvtRptInvtCategListDAO
{
    @Override
    public List findPlantList(InvtRptInvtCategListForm invtRptInvtCategListForm, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT 								");
        query.append("    x.invt_categ 		INVTCATEGID		");
        query.append("  , dbo.SFACODE_TO_DESC(x.invt_categ,'INVT_CATEG', 'SYS', x.comp_no, '"+loginUser.getLangId()+"') INVTCATEGDESC		");
        query.append("  , COUNT(1)    		INVTCATEGCNT	");
        query.append("FROM TAINVTLIST x						");
        query.append("WHERE 1=1								");
        query.append(this.getWhere(invtRptInvtCategListForm,loginUser));
        query.append("GROUP BY x.comp_no, x.invt_categ		");
        
        return getJdbcTemplate().queryForList(query.toString());
        
    }

    public String getWhere(InvtRptInvtCategListForm invtRptInvtCategListForm,User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        InvtRptInvtCategCommonDTO invtRptInvtCategCommonDTO = invtRptInvtCategListForm.getInvtRptInvtCategCommonDTO();

        query.getAndQuery("x.comp_no", loginUser.getCompNo());
        query.getAndQuery("x.invtlist_status", "C");
        query.append("AND EXISTS (SELECT 1 FROM TACDSYSD a 				");
        query.append("             WHERE a.list_type = 'INVT_CATEG' 	");
        query.append("               AND a.cdsysd_no = x.invt_categ)	");
        query.append("AND EXISTS (SELECT 1 FROM TACDSYSD a 				");
        query.append("             WHERE a.list_type = 'INVT_TYPE' 		");
        query.append("               AND a.cdsysd_no = x.invt_type)		");
        
        //사용부서
        query.getDeptLevelQuery("x.usage_dept", invtRptInvtCategCommonDTO.getFilterUsageDeptId(), invtRptInvtCategCommonDTO.getFilterUsageDeptDesc(), loginUser.getCompNo());

        //공장명
        query.getCodeLikeQuery("x.plant", "(SELECT a.description FROM  TAPLANT a WHERE a.comp_no = '"+loginUser.getCompNo()+"' AND a.plant = x.plant )", 
        		invtRptInvtCategCommonDTO.getFilterPlantId(), invtRptInvtCategCommonDTO.getFilterPlantDesc());
        
        //투자완료일자
        String fromDate = invtRptInvtCategCommonDTO.getFilterFromDate();
        String toDate   = invtRptInvtCategCommonDTO.getFilterToDate();
        
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