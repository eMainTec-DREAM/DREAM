package dream.pers.appstb.prc.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.pers.appreq.dto.AppReqCommonDTO;
import dream.pers.appstb.prc.dao.AppPrcListDAO;

/**
 * 결재문서 - 목록
 * @author  javaworker
 * @version $Id: AppPrcListDAO.java,v 1.1 2013/08/30 09:12:11 javaworker Exp $
 * @since   1.0
 * @spring.bean id="appPrcListDAO"
 * @spring.txbn id="appPrcListDAOTarget"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AppPrcListDAOOraImpl extends BaseJdbcDaoSupportOra implements AppPrcListDAO
{
    public List findAppPrcList(AppReqCommonDTO appReqCommonDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        
        query.append("SELECT                                                                        ");
        query.append("       ''                                            seqNo                    ");
        query.append("       ,''                                            isDelCheck              ");
        query.append("       ,appr_seq                                      apprSeq                 ");
        query.append("       ,y.emp_name                                    apprByName              ");
        query.append("       ,y.grade                                                               ");
        query.append("       ,(SELECT a.description                                                  ");
        query.append("        FROM   TADEPT a                                                       ");
        query.append("        WHERE  a.comp_no = y.comp_no and a.dept_id = y.dept_id)   deptName    ");
        query.append("       ,SFACODE_TO_DESC(x.apprusr_action,'APPRUSR_ACTION','SYS','','"+loginUser.getLangId()+"') apprusrAction           ");
        query.append("       ,SFACODE_TO_DESC(x.apprusr_status,'APPRUSR_STATUS','SYS','','"+loginUser.getLangId()+"') apprusrStatus           ");
        query.append("       ,SFACODE_TO_DESC(x.apprusr_type,'APPRUSR_TYPE','SYS','','"+loginUser.getLangId()+"') apprusrType           ");
        query.append("       ,TO_CHAR(TO_DATE(x.appr_date, 'yyyymmdd'),'yyyy-mm-dd') || ' ' ||  TO_CHAR(TO_DATE(appr_time, 'HH24MISS'),'HH24:MI') apprTime        ");
        query.append("       ,x.REMARK                                                                                                                            ");
        query.append("       ,x.apprusr_id     apprusrId                                                                                                          ");
        query.append("FROM TAAPPRUSR x, TAEMP y                         ");
        query.append("WHERE x.appr_by = y.emp_id                        ");
        query.append("    and  x.comp_no = y.comp_no                    ");
        query.append(this.getWhere(appReqCommonDTO, loginUser));
        query.getOrderByQuery("x.appr_date||x.appr_time, appr_seq", appReqCommonDTO.getOrderBy(), appReqCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(appReqCommonDTO.getIsLoadMaxCount(), appReqCommonDTO.getFirstRow()));
    }
    
    /**
     * Filter 조건
     * @author  javaworker
     * @version $Id: AppPrcListDAO.java,v 1.1 2013/08/30 09:12:11 javaworker Exp $
     * @since   1.0
     * 
     * @param appPrcCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(AppReqCommonDTO appReqCommonDTO, User loginUser)
    {        
        QueryBuffer query = new QueryBuffer();

        query.getAndQuery("x.comp_no", loginUser.getCompNo());
        query.getAndQuery("x.apprlist_id", appReqCommonDTO.getApprlistId());
        if (!"".equals(appReqCommonDTO.getApprusrId()))
        {
            query.getAndQuery("x.apprusr_id", appReqCommonDTO.getApprusrId());
            return query.toString();
        }
        
        if(!"".equals(appReqCommonDTO.getObjectId()) && !"".equals(appReqCommonDTO.getApprType()))
        {
            query.append(" AND  x.apprlist_id  = (SELECT                             ");
            query.append("                               z.apprlist_id apprlistId    ");
            query.append("                        FROM   TAAPPRLIST z                ");
            query.append("                        WHERE  1 = 1                       ");
            //query.append("                            and z.appr_status <> 'W'       ");
            query.getAndQuery("z.comp_no", loginUser.getCompNo());
            query.getAndQuery("z.object_id", appReqCommonDTO.getObjectId());
            query.getAndQuery("z.appr_type", appReqCommonDTO.getApprType());
            query.append("                       )                                   ");
            
            //결재이력에는 전체를 볼수 있게 처리.
            if (!"Y".equals(appReqCommonDTO.getIsAppHist()))
            {
                query.append(" AND  x.apprusr_action  != 'C'                         "); //[P:처리대상 C:처리완료 Z:대기]
            }
        }
                        
        return query.toString();
    }
    

    public int deleteLine(String id, String compNo)
    {
        QueryBuffer query = new QueryBuffer();

        query = new QueryBuffer();
        
        query.append("DELETE FROM TAAPPRUSR               ");
        query.append("WHERE apprusr_id     = ?            ");
        query.append("    and comp_no     = ?             ");
        
        Object[] objects = new Object[] {
        		id
        		,compNo
        };
        
        return this.getJdbcTemplate().update(query.toString(), objects);  
    }

	@Override
	public String findTotalCount(AppReqCommonDTO appReqCommonDTO, User loginUser) throws Exception {
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT                                ");
        query.append("       COUNT(1)                       ");
        query.append("FROM TAAPPRUSR x, TAEMP y             ");
        query.append("WHERE x.appr_by = y.emp_id            ");
        query.append("    and  x.comp_no = y.comp_no		");
        query.getAndQuery("x.comp_no", loginUser.getCompNo());
        query.append(this.getWhere(appReqCommonDTO, loginUser));

        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
	}
}