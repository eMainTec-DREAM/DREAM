package dream.work.rpt.maeqbm.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.work.rpt.maeqbm.dao.MaEqBmListDAO;
import dream.work.rpt.maeqbm.dto.MaEqBmListDTO;

/**
 * 설비고장내역DAO
 * @author  kim21017
 * @version $Id: MaEqBmListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maEqBmListDAOTarget"
 * @spring.txbn id="maEqBmListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaEqBmListDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaEqBmListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaEqBmListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqBmListDTO
     * @return List
     */
    public List findEqBmList(MaEqBmListDTO maEqBmListDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        String lang = user.getLangId();

        query.append("SELECT '' seqNo																																	");
		query.append("       ,x.wo_no woNo																																");
		query.append("       ,x.description woDesc																														");
        query.append("       ,																																			");
		query.getDate("x.start_date", "startDate");
		query.append("       ,(SELECT full_desc FROM TAEQLOC WHERE comp_no=y.comp_no AND eqloc_id=y.eqloc_id)      eqLocDesc      ");
		query.append("       ,y.description eqDesc																														");
		query.append("       ,(SELECT description FROM TADEPT WHERE comp_no = x.comp_no AND dept_id = x.dept_id) deptDesc												");
		query.append("       ,x.pm_type pmType																															");
		query.append("       ,dbo.SFACODE_TO_DESC(x.pm_type,x.wo_type+'_TYPE','SYS',x.comp_no,'"+user.getLangId()+"') pmTypeDesc										");
		query.append("       ,(SELECT emp_name FROM TAEMP WHERE comp_no = x.comp_no AND emp_id = x.emp_id) empDesc														");
		query.append("       ,CONVERT(VARCHAR,CONVERT(DATE,z.eqdn_start_date),120)+' '+SUBSTRING(eqdn_start_time,1,2)+':'+SUBSTRING(eqdn_start_time,3,2) eqDnStartTime	");
		query.append("       ,CONVERT(VARCHAR,CONVERT(DATE,z.eqdn_end_date),120)+' '+SUBSTRING(eqdn_end_time,1,2)+':'+SUBSTRING(eqdn_end_time,3,2) eqDnEndTime			");
		query.append("       ,z.eqdn_work_time eqDnWorkTime																												");
		query.append("       ,(SELECT description FROM TAEQASMB WHERE comp_no = z.comp_no AND eqasmb_id = z.eqasmb_id) eqAsmbDesc										");
		query.append("       ,(SELECT                                                                                                                                           ");
        query.append("                   (SELECT b.key_name                                                                                                                     ");
        query.append("                        FROM TALANG b                                                                                                                     ");
        query.append("                        WHERE  b.lang = '"+lang+"'                                                                                                          ");
        query.append("                        AND aa.key_type = b.key_type                                                                                                      ");
        query.append("                        AND aa.key_no = b.key_no)    description                                                                                              ");
        query.append("            FROM TAFAILURE aa WHERE aa.comp_no = z.comp_no AND aa.fail_type = 'MO' AND aa.failure_id = z.mo_cd) moCd                                      ");
        query.append("       ,z.mo_desc moDesc                                                                                                                      ");
		query.append("       ,(SELECT 																										 							");
        query.append("       			(select b.key_name																												");
        query.append("            			from talang b																												");
        query.append("            			where  b.lang = '"+lang+"'																									");
        query.append("            			and aa.key_type = b.key_type																								");
        query.append("            			and aa.key_no = b.key_no)	description																						");
		query.append("			FROM TAFAILURE aa WHERE aa.comp_no = z.comp_no AND aa.fail_type = 'CA' AND aa.failure_id = z.ca_cd) caCd								");
		query.append("       ,z.ca_desc caDesc																															");
		query.append("       ,(SELECT 																										 							");
        query.append("       			(select b.key_name																												");
        query.append("            			from talang b																												");
        query.append("            			where  b.lang = '"+lang+"'																									");
        query.append("            			and aa.key_type = b.key_type																								");
        query.append("            			and aa.key_no = b.key_no)	description																						");
		query.append("			FROM TAFAILURE aa WHERE aa.comp_no = z.comp_no AND aa.fail_type = 'RE' AND aa.failure_id = z.re_cd) reCd								");
		query.append("       ,z.re_desc reDesc																															");
		query.append("       ,x.perform perform																															");
		query.append("       ,x.wkor_id wkorId																															");
		query.append("       ,Y.equip_id eqId																															");
		query.append("       ,y.item_no equipNo																															");
		query.append("FROM TAWORKORDER x, TAWOFAIL z,																										");
        query.append("		(SELECT b.eqloc_id eqloc_id											");
        query.append("				,b.eqctg_id eqctg_id										");
        query.append("				,b.comp_no comp_no											");
        query.append("				,a.wkor_id wkor_id											");
        query.append("				,b.equip_id equip_id										");
        query.append("				,b.main_mng_id main_mng_id									");
        query.append("				,b.sub_mng_id sub_mng_id									");
        query.append("				,b.is_law_eq is_law_eq										");
        query.append("				,b.description description									");
        query.append("				,b.item_no item_no											");
        query.append("				,b.plf_type	plf_type											");
        query.append("		FROM TAWOEQUIP a, TAEQUIPMENT b										");
        query.append("		WHERE a.comp_no = b.comp_no 										");
        query.append("		AND a.equip_id = b.equip_id											");
        query.append("		AND a.comp_no = '"+maEqBmListDTO.getCompNo()+"') y					");
		query.append("WHERE x.comp_no = y.comp_no																														");
		query.append("  AND y.comp_no = z.comp_no																														");
		query.append("  AND x.wkor_id = y.wkor_id																														");
		query.append("  AND y.wkor_id = z.wkor_id																														");
		query.append(this.getWhere(maEqBmListDTO,user));
		query.getOrderByQuery("x.wkor_id", "x.start_date", maEqBmListDTO.getOrderBy(), maEqBmListDTO.getDirection());

		return getJdbcTemplate().queryForList(query.toString(maEqBmListDTO.getIsLoadMaxCount(), maEqBmListDTO.getFirstRow()));
    }
    /**
     * Filter 조건
     * @author  kim21017
     * @version $Id: MaEqBmListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqBmListDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MaEqBmListDTO maEqBmListDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	String compNo = maEqBmListDTO.getCompNo();
    	String lang   = user.getLangId();
    	//회사코드
    	query.getAndQuery("x.comp_no", compNo);
    	//고장작업
    	query.getAndQuery("x.wo_type", "BM");
    	//상태- 완료
    	query.getAndQuery("x.wo_status", "C");
    	//작업일자
		query.getAndDateQuery("x.start_date", maEqBmListDTO.getFilterStartDate(), maEqBmListDTO.getFilterEndDate());
		//부서
        query.getDeptLevelQuery("x.dept_id", maEqBmListDTO.getFilterDeptId(), maEqBmListDTO.getFilterDeptDesc(), compNo);
        //설비
        query.getCodeLikeQuery("y.equip_id", "y.description", maEqBmListDTO.getFilterEquipId(), maEqBmListDTO.getFilterEquipDesc());
        //위치
        query.getEqLocLevelQuery("y.eqloc_id", maEqBmListDTO.getFilterEqLocId(), maEqBmListDTO.getFilterEqLocDesc(), compNo);
        //종류
        query.getEqCtgLevelQuery("y.eqctg_id", maEqBmListDTO.getFilterEqCtgId(), maEqBmListDTO.getFilterEqCtgDesc(), compNo);
        //내/외자
    	query.getSysCdQuery("y.plf_type", maEqBmListDTO.getFilterPlfTypeId(), maEqBmListDTO.getFilterPlfTypeDesc(), "PLF_TYPE", compNo,user.getLangId());
    	//법정설비
        query.getLikeQuery("y.is_law_eq", maEqBmListDTO.getFilterIsLawEq());
        //관리자(정)
        query.getCodeLikeQuery("y.main_mng_id", "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = y.main_mng_id AND a.comp_no='"+compNo+"')", 
        		maEqBmListDTO.getFilterMainMngId(), maEqBmListDTO.getFilterMainMngName());
        //관리자(부)
        query.getCodeLikeQuery("y.sub_mng_id", "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = y.sub_mng_id AND a.comp_no='"+compNo+"')", 
        		maEqBmListDTO.getFilterSubMngId(), maEqBmListDTO.getFilterSubMngName());
        //작업형태
    	query.getSysCdQuery("x.pm_type", maEqBmListDTO.getFilterPmTypeId(), maEqBmListDTO.getFilterPmTypeDesc(), "x.wo_type+'_TYPE'", compNo,user.getLangId());
        //고장원인
    	query.getCodeLikeQuery("z.ca_cd", "(SELECT (select b.key_name from talang b where b.lang = '"+lang+"' and a.key_type=b.key_type and a.key_no = b.key_no) FROM TAFAILURE a WHERE a.comp_no = '"+compNo+"' AND fail_type='CA' AND a.failure_id = z.ca_cd)", maEqBmListDTO.getFilterCaCd(), maEqBmListDTO.getFilterCaCdDesc());
    	//고장조치
    	query.getCodeLikeQuery("z.re_cd", "(SELECT (select b.key_name from talang b where b.lang = '"+lang+"' and a.key_type=b.key_type and a.key_no = b.key_no) FROM TAFAILURE a WHERE a.comp_no = '"+compNo+"' AND fail_type='RE' AND a.failure_id = z.re_cd)", maEqBmListDTO.getFilterReCd(), maEqBmListDTO.getFilterReCdDesc());
    	//고장시간 (X분 이상)
    	if(!"".equals(maEqBmListDTO.getFilterFailTime())){
    		query.append("AND z.eqdn_work_time >= "+maEqBmListDTO.getFilterFailTime()+"");
    	}
    	//공장코드
        query.getCodeLikeQuery("x.plant", "(SELECT description FROM  TAPLANT WHERE comp_no = x.comp_no AND plant = x.plant )", 
        		maEqBmListDTO.getFilterPlantId(), maEqBmListDTO.getFilterPlantDesc());
        
    	
    	return query.toString();
    }
	@Override
	public String findTotalCount(MaEqBmListDTO maEqBmListDTO, User user)
	{
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SELECT 														");
		query.append("       COUNT(1)       										");
		query.append("FROM TAWORKORDER x, TAWOFAIL z,								");
        query.append("		(SELECT b.eqloc_id eqloc_id								");
        query.append("				,b.eqctg_id eqctg_id							");
        query.append("				,b.comp_no comp_no								");
        query.append("				,a.wkor_id wkor_id								");
        query.append("				,b.equip_id equip_id							");
        query.append("				,b.main_mng_id main_mng_id						");
        query.append("				,b.sub_mng_id sub_mng_id						");
        query.append("				,b.is_law_eq is_law_eq							");
        query.append("				,b.description description						");
        query.append("				,b.plf_type	plf_type											");
        query.append("		FROM TAWOEQUIP a, TAEQUIPMENT b							");
        query.append("		WHERE a.comp_no = b.comp_no 							");
        query.append("		AND a.equip_id = b.equip_id								");
        query.append("		AND a.comp_no = '"+user.getCompNo()+"') y				");
		query.append("WHERE x.comp_no = y.comp_no									");
		query.append("  AND y.comp_no = z.comp_no									");
		query.append("  AND x.wkor_id = y.wkor_id									");
		query.append("  AND y.wkor_id = z.wkor_id									");
		query.append(this.getWhere(maEqBmListDTO,user));
    	
    	List resultList = getJdbcTemplate().queryForList(query.toString());
        
		return QuerySqlBuffer.listToString(resultList);
	}
}