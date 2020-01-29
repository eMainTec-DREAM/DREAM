package dream.invt.rpt.leadtime.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import common.util.QuerySqlBuffer;
import dream.invt.rpt.leadtime.dao.InvtRptLeadTimeListDAO;
import dream.invt.rpt.leadtime.dto.InvtRptLeadTimeCommonDTO;
import dream.invt.rpt.leadtime.form.InvtRptLeadTimeListForm;

/**
 * InvtRptLeadTime Page - List DAO implements
 * @author cjscjs9
 * @version $Id: InvtRptLeadTimeListDAOOraImpl.java,v 1.0 2017/08/22 15:19:40 cjscjs9 Exp $
 * @since 1.0
 * 
 * @spring.bean id="invtRptLeadTimeListDAOTarget"
 * @spring.txbn id="invtRptLeadTimeListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class InvtRptLeadTimeListDAOOraImpl  extends BaseJdbcDaoSupportOra implements InvtRptLeadTimeListDAO
{

    /**
     * grid find
     * @author  cjscjs9
     * @version $Id: invtRptLeadTimeListDAO.java,v 1.0 2017/08/22 15:20:12 cjscjs9 Exp $
     * @since   1.0
     * 
     * @param invtRptLeadTimeCommonDTO
     * @return List
     */
    public List findList(InvtRptLeadTimeCommonDTO invtRptLeadTimeCommonDTO, User loginUser) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
       
        query.append("SELECT 	''  												isdelcheck			");
        query.append("          ,'' 												seqNo				");
        query.append("          ,SFACODE_TO_DESC(x.INVT_PROC_LTYPE, 'INVT_PROC_LTYPE', 'USR', x.comp_no, '"+loginUser.getLangId()+"')     lType        ");
        query.append("          ,SFACODE_TO_DESC(X.INVT_PROC_STYPE, 'INVT_PROC_STYPE', 'USR', x.comp_no, '"+loginUser.getLangId()+"')     sType        ");
        query.append("          ,ROUND(sum(to_date(x.end_date,'YYYYMMDD')-to_date(x.start_date,'YYYYMMDD')+1)/count (1),2)   			  avgLeadTime  ");
        query.append("          ,X.INVT_PROC_LTYPE              					LTYPEID        		");
        query.append("          ,X.INVT_PROC_STYPE             						STYPEID         	");
        query.append("FROM TAINVTPHASE x left outer join tainvtlist y       							");
        query.append("ON x.comp_no=y.comp_no AND x.invtlist_id=y.invtlist_id        					");
        query.append("WHERE 1=1        																	");
        query.append("AND x.invtphase_status = 'C'         												");
        query.append("AND x.end_date >= x.start_date        											");
        query.append(this.getWhere(invtRptLeadTimeCommonDTO,loginUser));
        query.append("GROUP BY x.INVT_PROC_LTYPE , x.INVT_PROC_STYPE, x.comp_no        					");
        query.getOrderByQuery("x.INVT_PROC_LTYPE", invtRptLeadTimeCommonDTO.getOrderBy(), invtRptLeadTimeCommonDTO.getDirection());
        

        
        return getJdbcTemplate().queryForList(query.toString(invtRptLeadTimeCommonDTO.getIsLoadMaxCount(),invtRptLeadTimeCommonDTO.getFirstRow()));
    }

    
    public List findChart(InvtRptLeadTimeCommonDTO invtRptLeadTimeCommonDTO, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
        query.append("SELECT 	  ''  													isdelcheck         ");
        query.append("            ,'' 													seqNo  		       ");
        query.append("            ,SFACODE_TO_DESC(x.INVT_PROC_LTYPE, 'INVT_PROC_LTYPE', 'USR', x.comp_no, '"+loginUser.getLangId()+"')     LTYPE        ");
        query.append("            ,SFACODE_TO_DESC(X.INVT_PROC_STYPE, 'INVT_PROC_STYPE', 'USR', x.comp_no, '"+loginUser.getLangId()+"')     STYPE        ");
        query.append("            ,sum(to_date(x.end_date,'YYYYMMDD')-to_date(x.start_date,'YYYYMMDD')+1)/count (1)     					AVGLEADTIME  ");
        query.append("            ,X.INVT_PROC_LTYPE              						LTYPEID       	   ");
        query.append("            ,X.INVT_PROC_STYPE             						STYPEID            ");
        query.append("FROM TAINVTPHASE x left outer join tainvtlist y       							   ");
        query.append("ON x.comp_no=y.comp_no AND x.invtlist_id=y.invtlist_id        					   ");
        query.append("WHERE 1=1        																	   ");
        query.append("AND x.invtphase_status = 'C'         												   ");
        query.append("AND x.end_date >= x.start_date        											   ");
        query.append(this.getWhere(invtRptLeadTimeCommonDTO,loginUser));
        query.append("GROUP BY x.INVT_PROC_LTYPE , x.INVT_PROC_STYPE, x.comp_no        					   ");
        
    	
        
    	return getJdbcTemplate().queryForList(query.toString());	
    }
    
    
    
    /**
     * Filter 조건
     * @author  cjscjs9
     * @version $Id: $
     * @since   1.0
     *   
     * @param invtRptLeadTimeCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(InvtRptLeadTimeCommonDTO invtRptLeadTimeCommonDTO, User loginUser)
    {        
        QueryBuffer query = new QueryBuffer();
        
        //회사
        query.getAndNumKeyQuery("x.comp_no", loginUser.getCompNo());
        
        // 일자
        query.getDateToDateQuery("x.start_date","x.end_date", invtRptLeadTimeCommonDTO.getFilterStartYear(), invtRptLeadTimeCommonDTO.getFilterEndYear());
        
        // 사용부서
        query.getDeptLevelQuery("y.usage_dept", invtRptLeadTimeCommonDTO.getFilterDeptId(), invtRptLeadTimeCommonDTO.getFilterDeptDesc(), loginUser.getCompNo());
        
        // 공장
        query.getCodeLikeQuery("y.plant", "(SELECT aa.description FROM  TAPLANT aa WHERE aa.comp_no = '"+loginUser.getCompNo()+"' AND aa.plant = y.plant )", 
        		invtRptLeadTimeCommonDTO.getFilterPlantId(), invtRptLeadTimeCommonDTO.getFilterPlantDesc());
        
        // 투자구분
        query.getSysCdQuery("y.invt_categ", invtRptLeadTimeCommonDTO.getFilterInvtCateg(), invtRptLeadTimeCommonDTO.getFilterInvtCategDesc(), "INVT_CATEG", loginUser.getCompNo(), loginUser.getLangId());
        // 분류
        query.getSysCdQuery("y.invt_type", invtRptLeadTimeCommonDTO.getFilterInvtType(), invtRptLeadTimeCommonDTO.getFilterInvtTypeDesc(), "INVT_TYPE", loginUser.getCompNo(), loginUser.getLangId());
        

        
        return query.toString();
    }

    public String findTotalCount(
            InvtRptLeadTimeCommonDTO invtRptLeadTimeCommonDTO, User user) {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT count(1)        											");
        query.append("FROM        														");
        query.append("(SELECT 	  ''  								isdelcheck        	");
        query.append("            ,'' 								seqNo        		");
        query.append("            ,SFACODE_TO_DESC(x.INVT_PROC_LTYPE, 'INVT_PROC_LTYPE', 'USR', x.comp_no, '"+user.getLangId()+"')     lType        ");
        query.append("            ,SFACODE_TO_DESC(X.INVT_PROC_STYPE, 'INVT_PROC_STYPE', 'USR', x.comp_no, '"+user.getLangId()+"')     sType        ");
        query.append("            ,'' 								avgLeadTime        	");
        query.append("FROM TAINVTPHASE x        										");
        query.append("WHERE 1=1        													");
        query.append("AND x.invtphase_status = 'C'         								");
        query.append("GROUP BY x.INVT_PROC_LTYPE , x.INVT_PROC_STYPE, x.comp_no)        ");
        
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }    
}
