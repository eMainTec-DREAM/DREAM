package dream.scheduler.autoschedule.dao.oraImpl;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import common.exception.SqlIgnoreException;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.CommonUtil;
import common.util.DateUtil;
import common.util.QueryBuffer;
import dream.scheduler.autoschedule.dao.MaBatchDAO;

/**
 * Batch DAO
 * @author  kim21017
 * @version $Id: MaBatchDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maBatchDAOTarget"
 * @spring.txbn id="maBatchDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaBatchDAOOraImpl extends BaseJdbcDaoSupportOra implements MaBatchDAO
{
    public void SP_PM_MAKE_SCHEDULE_BYALL(String compNo, String userNo) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("begin                                                 ");
        query.append("SP_PM_MAKE_SCHEDULE_BYALL('"+compNo+"','"+userNo+"'); ");
        query.append("end;                                                  ");
        
        this.getJdbcTemplate().execute(query.toString());
    }
    public void SP_PM_MAKE_TO_ALLSCHED(String compNo, String userNo) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("begin                                                 ");
        query.append("SP_PM_MAKE_TO_ALLSCHED('"+compNo+"','"+userNo+"');      ");
        query.append("end;                                                  ");
        
        this.getJdbcTemplate().execute(query.toString());
    }
    public void SEND_WORATE_EMAIL(String compNo, String userNo, String email, String contents) throws Exception
    {
        QueryBuffer query = new QueryBuffer();

        query.append("INSERT INTO TXNOTIMAIL(");
        query.append("		notimail_id,			comp_no,		cre_date,				");
        query.append("		sender,					title,			contents,				");
        query.append("		receiver														");
        query.append("	) 			 														");
        query.append("SELECT SQANOTIMAIL_ID.NEXTVAL,	?,	TO_CHAR(sysdate,'yyyymmdd'),	");
        query.append("		?,							x.description,	?,					");
        query.append("		y.e_mail														");
        query.append("FROM TAMAILLIST x, TAMAILUSR y										");
        query.append("WHERE x.comp_no = y.comp_no											");
        query.append("AND x.mail_list_id = y.mail_list_id									");
        query.append("AND x.comp_no = ?														");
        
        Object[] objects = new Object[] {
        		compNo,
        		email,
        		contents,
        		compNo
        };
        
        this.getJdbcTemplate().update(query.toString(),objects);
    }
    public void SP_MAKE_USE_MONITORING(String compNo, String userNo) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("begin                                                 ");
        query.append("SP_MAKE_USE_MONITORING('"+compNo+"','"+userNo+"');       ");
        query.append("end;                                                  ");
        
        this.getJdbcTemplate().execute(query.toString());
    }
    public void SP_PM_MAKE_TAMTPOINT(String compNo, String userNo) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("begin                                                 ");
        query.append("SP_PM_MAKE_TAMTPOINT('"+compNo+"','"+userNo+"');      ");
        query.append("end;                                                  ");
        
        this.getJdbcTemplate().execute(query.toString());
    }
    public void SP_MAKE_TAINVESTAMT(String compNo, String userNo) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("begin                                                 ");
        query.append("SP_MAKE_TAINVESTAMT('"+compNo+"','"+userNo+"');    ");
        query.append("end;                                                  ");
        
        this.getJdbcTemplate().execute(query.toString());
    }
    public void SP_MAKE_TALNWRKTIME(String compNo, String userNo) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("begin                                                 ");
        query.append("SP_MAKE_TALNWRKTIME('"+compNo+"','"+userNo+"');    ");
        query.append("end;                                                  ");
        
        this.getJdbcTemplate().execute(query.toString());
    }
    public void SP_SETDEFAULT_DUMYDAYS(String compNo, String userNo) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("begin                                                 ");
        query.append("SP_SETDEFAULT_DUMYDAYS('"+compNo+"','"+userNo+"');    ");
        query.append("end;                                                  ");
        
        this.getJdbcTemplate().execute(query.toString());
    }
    public void SP_KPI_MAKE_TAKPIDLOCDN(String compNo, String userNo) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("begin                                                 ");
        query.append("SP_KPI_MAKE_TAKPIDLOCDN('"+compNo+"','"+userNo+"');    ");
        query.append("end;                                                  ");
        
        this.getJdbcTemplate().execute(query.toString());
    }
    public void SP_KPI_MAKE_TAKPIMLOCDN(String compNo, String userNo) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("begin                                                 ");
        query.append("SP_KPI_MAKE_TAKPIMLOCDN('"+compNo+"','"+userNo+"');    ");
        query.append("end;                                                  ");
        
        this.getJdbcTemplate().execute(query.toString());
    }
    public void SP_KPI_MAKE_TAKPIDLOCCTGDN(String compNo, String userNo) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("begin                                                 ");
        query.append("SP_KPI_MAKE_TAKPIDLOCCTGDN('"+compNo+"','"+userNo+"');    ");
        query.append("end;                                                  ");
        
        this.getJdbcTemplate().execute(query.toString());
    }
    public void SP_KPI_MAKE_TAKPIMLOCCTGDN(String compNo, String userNo) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("begin                                                 ");
        query.append("SP_KPI_MAKE_TAKPIMLOCCTGDN('"+compNo+"','"+userNo+"');    ");
        query.append("end;                                                  ");
        
        this.getJdbcTemplate().execute(query.toString());
    }
    public void SP_KPI_MAKE_TAKPIMMPOINT(String compNo, String userNo) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("begin                                                 ");
        query.append("SP_KPI_MAKE_TAKPIMMPOINT('"+compNo+"','"+userNo+"');    ");
        query.append("end;                                                  ");
        
        this.getJdbcTemplate().execute(query.toString());
    }
    public void SP_KPI_MAKE_TAKPIWMPOINT(String compNo, String userNo) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("begin                                                 ");
        query.append("SP_KPI_MAKE_TAKPIWMPOINT('"+compNo+"','"+userNo+"');    ");
        query.append("end;                                                  ");
        
        this.getJdbcTemplate().execute(query.toString());
    }
    public void SP_KPI_MAKE_TAKPIMEDU(String compNo, String userNo) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("begin                                                 ");
        query.append("SP_KPI_MAKE_TAKPIMEDU('"+compNo+"','"+userNo+"');    ");
        query.append("end;                                                  ");
        
        this.getJdbcTemplate().execute(query.toString());
    }
    public void SP_KPI_MAKE_TAPTMONTHLYSTOCK(String compNo, String userNo) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("begin                                                 ");
        query.append("SP_KPI_MAKE_TAPTMONTHLYSTOCK('"+compNo+"','"+userNo+"');    ");
        query.append("end;                                                  ");
        
        this.getJdbcTemplate().execute(query.toString());
    }
	
	public String[] getIfCompNo() throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT comp_no			");
        query.append("FROM TACOMP				");
        query.append("WHERE IS_USE='Y'			");
        
		return QueryBuffer.toStringSingleArray(this.getJdbcTemplate().queryForList(query.toString()));
    }
	public String getIfUserNo() throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT value$							");
        query.append("FROM TACONFIG							");
        query.append("WHERE name='BATCH_EXEC_USER_ID'		");
        
		return QueryBuffer.listToString(this.getJdbcTemplate().queryForList(query.toString()));
    }
	public String getIfMailAddress() throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT value$							");
        query.append("FROM TACONFIG							");
        query.append("WHERE name='MAIL_SENDER_ID'			");
        
		return QueryBuffer.listToString(this.getJdbcTemplate().queryForList(query.toString()));
    }
	public String[][] getMailContents(String compNo) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        String date = "TO_CHAR(SYSDATE-1,'yyyymmdd')";
        
        String[] titleArr = {"targetUsrCnt","connUsrCnt","connCnt","woPlanCnt","woCompCnt","woRunRate","pmMstrCnt","pmWoPlan","pmWoComp","pmWoRunRate"};
        String[] titleArrKo = {"대상자수","접속자수","접속횟수","작업계획(건)","작업완료(건)","작업실행율(%)","예방작업기준건수","예방작업계획","예방작업완료","예방작업실행율(%)"};
        String totalKo = "전체";
        String lang = "ko";
        
        for (int i = 0; i < titleArr.length; i++) {
			if(i!=0){
				query.append("UNION ALL");
			}
			query.append("SELECT	NVL(MAX((SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no = '"+titleArr[i]+"')),'"+titleArrKo[i]+"')||'' AS TITLE		");
		
			switch (i) {
			case 0:
				query.append(", NVL(count(1),0)||'' AS a		");
				break;
			case 1:
				query.append(" ,NVL(sum(decode(b.login_date,"+date+",count(DISTINCT b.user_id),0)),0)||'' ");
				break;
			case 2:
				query.append(" ,NVL(sum(decode(b.login_date,"+date+",count(b.user_id),0)),0)||'' ");
				break;
			case 3:
				query.append(" ,NVL(sum(decode(x.start_date,"+date+",count(x.start_date),0)),0)||''");
				break;
			case 4:
				query.append(" ,NVL(sum(decode(x.start_date,"+date+",count(x.start_date),0)),0)||''");
				break;
			case 5:
				query.append(",NVL(ROUND(sum(decode(x.wo_status,'C',decode(x.start_date,"+date+",1,0),0))/	");
				query.append("	DECODE(sum(decode(x.start_date,"+date+",1,0)),0,NULL		");
				query.append("	,sum(decode(x.start_date,"+date+",1,0)))*100,2),0)||''		");
				break;
			case 6:
				query.append(",NVL(count(1),0)||''		");
				break;
			case 7:
				query.append(",NVL(sum(decode(d.start_date,"+date+",1,0)),0)||''				");
				break;
			case 8:
				query.append(",NVL(sum(decode(d.start_date,"+date+",1,0)),0)||''				");
				break;
			case 9:
				query.append(",NVL(ROUND(sum(decode(x.pmsched_status,'C',decode(d.start_date,"+date+",1,0),0))/		");
				query.append("		DECODE(sum(decode(d.start_date,"+date+",1,0)),0,NULL		");
				query.append("		,sum(decode(d.start_date,"+date+",1,0)))*100,2),0)||''");
				break;
			default:
				break;
			}
			switch (i) {
			case 0:
				query.append("FROM TAUSER		");
				query.append("WHERE 1=1			");
				query.getAndQuery("comp_no", compNo);
				query.getAndQuery("is_monitor", "Y");
				break;
			case 1:
				query.append("FROM TAUSER a,		");
				query.append("(							");
				query.append("SELECT * FROM TADAY x, TALOGINCCLOG y		");
				query.append("WHERE x.tday = y.login_date		");
				query.getAndQuery("y.comp_no", compNo);
				query.append("AND x.tday = "+date+"");
				query.append(") b					");
				query.append("WHERE a.comp_no = b.comp_no		");
				query.append("AND a.user_id = b.user_id		");
				query.getAndQuery("a.comp_no", compNo);
				query.getAndQuery("a.is_monitor", "Y");
				query.append("GROUP BY b.comp_no , b.login_date, b.user_id	");
				break;
			case 2:
				query.append("FROM TAUSER a,		");
				query.append("(							");
				query.append("SELECT * FROM TADAY x, TALOGINCCLOG y		");
				query.append("WHERE x.tday = y.login_date		");
				query.append("AND x.tday = "+date+"");
				query.getAndQuery("y.comp_no", compNo);
				query.append(") b					");
				query.append("WHERE a.comp_no = b.comp_no		");
				query.append("AND a.user_id = b.user_id		");
				query.getAndQuery("a.comp_no", compNo);
				query.getAndQuery("a.is_monitor", "Y");
				query.append("GROUP BY b.comp_no , b.login_date, b.user_id	");
				break;
			case 3: 
				query.append("FROM TAWORKORDER x		");
				query.append("WHERE 1=1					");
				query.getAndQuery("x.comp_no", compNo);
				query.append("AND x.start_date = "+date+"");
				query.append("GROUP BY x.start_date");
				break;
			case 4:
				query.append("FROM TAWORKORDER x				");
				query.append("WHERE 1=1							");
				query.getAndQuery("x.comp_no", compNo);
				query.getAndQuery("x.wo_status", "C");
				query.append("AND x.start_date = "+date+"");
				query.append("GROUP BY x.start_date");
				break;
			case 5:
				query.append("FROM tAWORKORDER x				");
				query.append("WHERE 1=1							");
				query.getAndQuery("x.comp_no", compNo);
				break;
			case 6:
				query.append("FROM TAPMLST x					");
				query.append("WHERE 1=1							");
				query.getAndQuery("x.comp_no", compNo);
				break;
			case 7:
				query.append("FROM TAPMSCHED x, TAPMLST y, TAWORKORDER d	");
				query.append("WHERE x.comp_no = y.comp_no	");
				query.append("AND y.comp_no = d.comp_no		");
				query.append("AND x.pm_id = y.pm_id			");
				query.append("AND x.wkor_id = d.wkor_id		");
				query.getAndQuery("x.comp_no", compNo);
				break;
			case 8:
				query.append("FROM TAPMSCHED x, TAPMLST y, TAWORKORDER d	");
				query.append("WHERE x.comp_no = y.comp_no	");
				query.append("AND y.comp_no = d.comp_no		");
				query.append("AND x.pm_id = y.pm_id			");
				query.append("AND x.wkor_id = d.wkor_id		");
				query.getAndQuery("x.comp_no", compNo);
				query.getAndQuery("x.pmsched_status", "C");
				break;
			case 9:
				query.append("FROM TAPMSCHED x, TAPMLST y, TAWORKORDER d	");
				query.append("WHERE x.comp_no = y.comp_no	");
				query.append("AND y.comp_no = d.comp_no		");
				query.append("AND x.pm_id = y.pm_id			");
				query.append("AND x.wkor_id = d.wkor_id		");
				query.getAndQuery("x.comp_no", compNo);
				break;
			default:
				break;
			}
        }
        
		return QueryBuffer.toStringArray(this.getJdbcTemplate().queryForList(query.toString()));
    }
	
    /**
     * ERP 자재 싱크 프로시져
     * @author  jung7126
     * @version $Id:$
     * @since   1.0
     * 
     * @param compNo
     * @param userNo
     */
    public void SP_IF_UPD_TAPARTS(String compNo, String userNo)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("begin                                                 ");
        query.append("SP_IF_UPD_TXPARTS('"+compNo+"','"+userNo+"');         ");
        query.append("end;                                                  ");
        
        this.getJdbcTemplate().execute(query.toString());
    }
    
    public List getErpCode(String compNo)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                            ");
        query.append("       x.out_wcode outWcode,      ");
        query.append("       x.out_plant outPlant,      ");
        query.append("       y.bukrs,                   ");
        query.append("       x.wcode_id wcodeId,        "); 
        query.append("       x.gsber                    ");
        query.append("FROM   TAWAREHOUSE x, TXERPCOMP y ");
        query.append("WHERE  x.comp_no = y.comp_no      ");
        query.append("  AND x.wh_type = 'LEGACY'        ");
        query.getAndQuery("x.comp_no", compNo);

        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    public void SP_IF_UPD_TAPTSTOCK(String compNo, String userNo)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("begin                                                 ");
        query.append("SP_IF_UPD_TAPTSTOCK('"+compNo+"','"+userNo+"');       ");
        query.append("end;                                                  ");
        
        this.getJdbcTemplate().execute(query.toString());
    }
    public void SP_IF_UPD_TAERPISSHIST(String compNo, String userNo)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("begin                                                 ");
        query.append("SP_IF_UPD_TAERPISSHIST('"+compNo+"','"+userNo+"');    ");
        query.append("end;                                                  ");
        
        this.getJdbcTemplate().execute(query.toString());
    }
    public void SP_IF_UPD_TXERPPRPOLIST(String compNo, String userNo)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("begin                                                 ");
        query.append("SP_IF_UPD_TXERPPRPOLIST('"+compNo+"','"+userNo+"');   ");
        query.append("end;                                                  ");
        
        this.getJdbcTemplate().execute(query.toString());
    }
    
    public void SP_IF_UPD_TXLNWRKTIME(String compNo, String userNo)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("begin                                                 ");
        query.append("SP_IF_UPD_TXLNWRKTIME('"+compNo+"','"+userNo+"');     ");
        query.append("end;                                                  ");
        
        this.getJdbcTemplate().execute(query.toString());
    }
    
    public void SP_IF_UPD_TXLNNTWRKTIME(String compNo, String userNo)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("begin                                                 ");
        query.append("SP_IF_UPD_TXLNNTWRKTIME('"+compNo+"','"+userNo+"');   ");
        query.append("end;                                                  ");
        
        this.getJdbcTemplate().execute(query.toString());
    }
    
    public List findGlobalApi(String compNo)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("SELECT yyyymm, comp_no, plant ,(SELECT a.description FROM TACOMP a WHERE a.comp_no = comp_no) comp_name ");
    	query.append("       ,MAX(DECODE(kpi_mpoint, 'BD', m_plan)) bd_rate_plan     	");
    	query.append("       ,MAX(DECODE(kpi_mpoint, 'BD', m_actual)) bd_rate_actual    ");
    	query.append("       ,MAX(DECODE(kpi_mpoint, 'MTBF', m_plan)) mtbf_plan     	");
    	query.append("       ,MAX(DECODE(kpi_mpoint, 'MTBF', m_actual)) mtbf_actual     ");
    	query.append("       ,MAX(DECODE(kpi_mpoint, 'MTTR', m_plan)) mttr_plan     	");
    	query.append("       ,MAX(DECODE(kpi_mpoint, 'MTTR', m_actual)) mttr_actual     ");
    	query.append("       ,MAX(DECODE(kpi_mpoint, 'PMI', m_plan)) pmi_plan     		");
    	query.append("       ,MAX(DECODE(kpi_mpoint, 'PMI', m_actual)) pmi_actual     	");
    	query.append("       ,MAX(DECODE(kpi_mpoint, 'PMI', m_ach)) pmi_rate     		");
    	query.append("       ,MAX(DECODE(kpi_mpoint, 'PMW', m_plan)) pmw_plan     		");
    	query.append("       ,MAX(DECODE(kpi_mpoint, 'PMW', m_actual)) pmw_actual     	");
    	query.append("       ,MAX(DECODE(kpi_mpoint, 'PMW', m_ach)) pmw_rate     		");
    	query.append("FROM   TAKPIMMPOINT     											");
    	query.append("WHERE  yyyymm = TO_CHAR(sysdate, 'yyyymm')						");
    	query.append("  AND  comp_no = ?												");		
    	query.append("group by comp_no, plant, yyyymm     								");
    	
    	Object[] objects = new Object[] {
    			compNo
    	};
    	
    	 return getJdbcTemplate().queryForList(query.toString(), objects);

    }
    
    public void insertGlobalApi(Map map) throws IOException
    {
        QueryBuffer query = new QueryBuffer();

        query.append("MERGE INTO TXGLKPI a                              ");
        query.append("USING(    SELECT                                  ");
        query.append("                  ? comp_no,                      ");//1
        query.append("                  ? yyyymm,                       ");
        query.append("                  ? comp_name,                    ");
        query.append("                  ? pmw_rate,                     ");//pmw_rate
        query.append("                  ? bd_rate_plan,                 ");//bd_rate_plan
        query.append("                  ? bd_rate_actual,               "); //bd_rate_actual
        query.append("                  ? mtbf_plan,                    "); // mtbf_plan 
        query.append("                  ? mtbf_actual,                  "); //mtbf_actual 
        query.append("                  ? mttr_plan,                    "); //mttr_plan 
        query.append("                  ? mttr_actual,                  "); // mttr_actual 
        query.append("                  ? pmi_plan,                     "); //pmi_plan
        query.append("                  ? pmi_actual,                   "); // pmi_actual 
        query.append("                  ? pmi_rate,                     "); //pmi_rate
        query.append("                  ? pmw_actual,                   "); // pmw_actual
        query.append("                  ? pmw_plan                      "); // pmw_plan
        query.append("          FROM DUAL) b                            ");
        query.append("ON(   a.comp_no = b.comp_no                       ");
        query.append("  AND a.yyyymm = b.yyyymm                         ");
        query.append("  )                                               ");
        query.append("WHEN MATCHED THEN                                 ");
        query.append("  UPDATE SET                                      ");
        query.append("         a.comp_name = b.comp_name                ");
        query.append("         ,a.pmw_rate = b.pmw_rate                 ");
        query.append("         ,a.bd_rate_plan = b.bd_rate_plan         ");
        query.append("         ,a.bd_rate_actual = b.bd_rate_actual     ");
        query.append("         ,a.mtbf_plan = b.mtbf_plan               ");
        query.append("         ,a.mtbf_actual = b.mtbf_actual           ");
        query.append("         ,a.mttr_plan = b.mttr_plan               ");
        query.append("         ,a.mttr_actual = b.mttr_actual           ");
        query.append("         ,a.pmi_plan = b.pmi_plan                 ");
        query.append("         ,a.pmi_actual = b.pmi_actual             ");
        query.append("         ,a.pmi_rate = b.pmi_rate                 ");
        query.append("         ,a.pmw_plan = b.pmw_plan                 ");
        query.append("         ,a.pmw_actual = b.pmw_actual             ");
        query.append("WHEN NOT MATCHED THEN                             ");
       query.append("INSERT                                             ");
        query.append("(                                                         ");
        query.append("        pmw_rate          ,yyyymm                         ");
        query.append("       ,comp_no           ,comp_name      ,bd_rate_plan   ");
        query.append("       ,bd_rate_actual    ,mtbf_plan      ,mtbf_actual    ");
        query.append("       ,mttr_plan         ,mttr_actual    ,pmi_plan       ");
        query.append("       ,pmi_actual        ,pmi_rate       ,pmw_plan       ");
        query.append("       ,pmw_actual                                        ");
        query.append(") VALUES (                                                ");
        query.append("         b.pmw_rate                   ,b.yyyymm                                           ");
        query.append("        ,b.comp_no                    ,b.comp_name              ,b.bd_rate_plan           ");
        query.append("        ,b.bd_rate_actual             ,b.mtbf_plan              ,b.mtbf_actual            ");
        query.append("        ,b.mttr_plan                  ,b.mttr_actual            ,b.pmi_plan               ");
        query.append("        ,b.pmi_actual                 ,b.pmi_rate               ,b.pmw_plan               ");
        query.append("        ,b.pmw_actual                                                                     ");
        query.append(")                                                         ");
        
        Object[] objects = new Object[] {
                convertString(map.get("COMP_NO")),
                convertString(map.get("YYYYMM")),
                convertString(map.get("COMP_NAME")),
                convertString(map.get("PMW_RATE")),
                convertString(map.get("BD_RATE_PLAN")),
                convertString(map.get("BD_RATE_ACTUAL")),
                convertString(map.get("MTBF_PLAN")),
                convertString(map.get("MTBF_ACTUAL")),
                convertString(map.get("MTTR_PLAN")),
                convertString(map.get("MTTR_ACTUAL")),
                convertString(map.get("PMI_PLAN")),
                convertString(map.get("PMI_ACTUAL")),
                convertString(map.get("PMI_RATE")),
                convertString(map.get("PMW_ACTUAL")),
                convertString(map.get("PMW_PLAN"))

        };

        getJdbcTemplate().update(query.toString(), objects);
        
//      CommonUtil.jdbcConnUpdate(MwareConfig.getGlobalDriveName(), MwareConfig.getGlobalUrl(), 
//                MwareConfig.getGlobalUserName(), MwareConfig.getGlobalPassword(), query.toString(), objects); 

    }
    
	/**
	 * Global API Parts Stock Info
	 * @author  Mark
	 * @version $Id: codetemplates.xml,v 1.1 2007/02/15 06:41:03 dawn Exp $
	 * @since   1.0
	 * 
	 * @param compNo
	 * @return
	 */
	public List<Map> findGlobalApiParts(String compNo) {
	    
	    QueryBuffer query = new QueryBuffer();
	    
	    query.append("SELECT                                                           ");
	    query.append("        comp_no compNo                                           ");
	    query.append("        ,(SELECT description                                     ");
	    query.append("          FROM   TACOMP                                          ");
	    query.append("          WHERE  comp_no = b.comp_no) compName                   ");
	    query.append("        ,wcode                                                   ");
	    query.append("        ,wname                                                   ");
	    query.append("        ,part_no partNo                                          ");
	    query.append("        ,description                                             ");
	    query.append("        ,pt_size ptSize                                          ");
	    query.append("        ,MAX(DECODE(part_grade, 'A', stock_qty, '0')) astockQty  ");
	    query.append("        ,MAX(DECODE(part_grade, 'B', stock_qty, '0'))bstockQty   ");
	    query.append("FROM (                                                           ");
	    query.append("SELECT                                                           ");
	    query.append("        y.comp_no                                                ");
	    query.append("        ,y.part_no                                               ");
	    query.append("        ,y.description                                           ");
	    query.append("        ,x.wcode_id wcode                                        ");
	    query.append("        ,(SELECT a.wname                                         ");
	    query.append("           FROM  TAWAREHOUSE a                                   ");
	    query.append("           WHERE a.wcode_id = x.wcode_id) wname                  ");
	    query.append("        ,y.pt_size                                               ");
	    query.append("        ,x.stock_qty                                             ");
	    query.append("        ,x.part_grade                                            ");
	    query.append("FROM TAPTSTOCK x, TAPARTS y                                      ");
	    query.append("WHERE x.part_id = y.part_id                                      ");
	    query.append("  AND y.part_no is not null                                      ");
	    query.append("  AND y.comp_no = ?                                              ");
	    query.append(") b                                                              ");
	    query.append("GROUP BY comp_no, wcode, wname, part_no, description, pt_size    ");
	    query.append("ORDER BY part_no                                                 ");

	    Object[] objects = new Object[] {
                compNo
        };
        
         return getJdbcTemplate().queryForList(query.toString(), objects, Map.class);
	}

    public String insertGlobalPartApi() throws IOException
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("MERGE INTO TXGLSTOCK a                ");
        query.append("USING(    SELECT                      ");
        query.append("                  ? compNo,           ");
        query.append("                  ? wcode,            ");
        query.append("                  ? partNo,           ");
        query.append("                  ? astockQty,        ");
        query.append("                  ? bstockQty,        ");
        query.append("                  ? wname,            ");
        query.append("                  ? description,      ");
        query.append("                  ? ptSize,           ");
        query.append("                  ? compName          ");
        query.append("          FROM DUAL) b                ");
        query.append("ON(   a.comp_no = b.compNo            ");
        query.append("  AND a.wcode = b.wcode               ");
        query.append("  AND a.part_no = b.partNo )          ");
        query.append("WHEN MATCHED THEN                     ");
        query.append("  UPDATE SET                          ");
        query.append("         a.astock_qty = b.astockQty   ");
        query.append("         ,a.bstock_qty = b.bstockQty  ");
        query.append("         ,a.comp_name = b.compName    ");
        query.append("WHEN NOT MATCHED THEN                 ");
        query.append("  INSERT (                            ");
        query.append("          a.comp_no       ,a.comp_name                        ");
        query.append("         ,a.wcode         ,a.wname        ,a.part_no          ");
        query.append("         ,a.description  ,a.pt_size       ,a.astock_qty       ");
        query.append("         ,a.bstock_qty                                        ");
        query.append("         )                                                    ");
        query.append("  VALUES (b.compNo,       b.compName,                         ");
        query.append("          b.wcode,        b.wname,        b.partNo,           ");
        query.append("          b.description,  b.ptSize,       b.astockQty,        ");
        query.append("          b.bstockQty                                         ");
        query.append("         )                                                    ");
        
        return query.toString();
    }
    
    public void executeBatch(String queryStr, final List<Object[]> paramList) throws IOException
    {
        getJdbcTemplate().batchUpdate(queryStr, new BatchPreparedStatementSetter() {
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Object[] obj = paramList.get(i);
               
                ps.setString(1, String.valueOf(obj[0]));
                ps.setString(2, String.valueOf(obj[1]));
                ps.setString(3, String.valueOf(obj[2]));
                ps.setString(4, String.valueOf(obj[3]));
                ps.setString(5, String.valueOf(obj[4]));
                ps.setString(6, String.valueOf(obj[5]));
                ps.setString(7, String.valueOf(obj[6]));
                ps.setString(8, String.valueOf(obj[7]));
                ps.setString(9, String.valueOf(obj[8]));
              }

              public int getBatchSize() {
                return paramList.size();
              }
            });
        
//      CommonUtil.jdbcConnBatch(MwareConfig.getGlobalDriveName(), MwareConfig.getGlobalUrl(), 
//              MwareConfig.getGlobalUserName(), MwareConfig.getGlobalPassword(), queryStr, paramList); 
//      CommonUtil.jdbcConnBatch("oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:@10.122.246.190:1521:gmms", 
//              "dymos_dev", "dymos_dev", queryStr, paramList); 
    }

	public void SP_WOMAKE_4WP_BYALL(String compNo) {
		QueryBuffer query = new QueryBuffer();
        
        query.append("begin                                                 ");
        query.append("SP_WOMAKE_4WP_BYALL('"+compNo+"');    				");
        query.append("end;                                                  ");
        
        this.getJdbcTemplate().execute(query.toString());
	}
	public void DAEWOONG_IF_POP_EQ(String compNo, String userNo) throws SqlIgnoreException{
	
		try{
			QueryBuffer query = new QueryBuffer();
	        
	        query.append("begin                                                 ");
	        query.append("DAEWOONG_IF_POP_EQ('"+compNo+"', '"+userNo+"');    	");
	        query.append("end;                                                  ");
	        
	        this.getJdbcTemplate().execute(query.toString());
		}catch(Exception e) {
	        throw new SqlIgnoreException(e.getMessage());
	    }
		
	}
	public void DAEWOONG_IF_POP_EQACT(String compNo, String userNo) throws SqlIgnoreException{
		try{
			QueryBuffer query = new QueryBuffer();
	        
	        query.append("begin                                                 ");
	        query.append("DAEWOONG_IF_POP_EQACT('"+compNo+"', '"+userNo+"');  	");
	        query.append("end;                                                  ");
	        
	        this.getJdbcTemplate().execute(query.toString());
		}catch(Exception e) {
	        throw new SqlIgnoreException(e.getMessage());
	    }
	}
	public void DAEWOONG_IF_UPD_PT_SAFTY_STOCK(String compNo, String userNo) throws SqlIgnoreException{
		try{
			QueryBuffer query = new QueryBuffer();
	        
	        query.append("begin                                                 		");
	        query.append("DAEWOONG_IF_UPD_PT_SAFTY_STOCK('"+compNo+"', '"+userNo+"');   ");
	        query.append("end;                                                  		");
	        
	        this.getJdbcTemplate().execute(query.toString());
		}catch(Exception e) {
	        throw new SqlIgnoreException(e.getMessage());
	    }
	}
	public void DAEWOONG_IF_UPD_USER(String compNo, String userNo) throws SqlIgnoreException{
		try{
			QueryBuffer query = new QueryBuffer();
	        
	        query.append("begin                                                 		");
	        query.append("DAEWOONG_IF_UPD_USER('"+compNo+"', '"+userNo+"');   			");
	        query.append("end;                                                  		");
	        
	        this.getJdbcTemplate().execute(query.toString());
		}catch(Exception e) {
	        throw new SqlIgnoreException(e.getMessage());
	    }
	}
	
	public void SP_IF_UPD_EMP(String compNo, String userNo) throws SqlIgnoreException{
		try{
			QueryBuffer query = new QueryBuffer();
	        
	        query.append("begin                                                 ");
	        query.append("SP_IF_UPD_EMP('"+compNo+"', '"+userNo+"');    		");
	        query.append("end;                                                  ");
	        
	        this.getJdbcTemplate().execute(query.toString());
		}catch(Exception e) {
	        throw new SqlIgnoreException(e.getMessage());
	    }
	}
	public void SP_IF_UPD_VENDOR(String compNo, String userNo) throws SqlIgnoreException{
		try{
			QueryBuffer query = new QueryBuffer();
	        
	        query.append("begin                                                 ");
	        query.append("SP_IF_UPD_VENDOR('"+compNo+"', '"+userNo+"');    		");
	        query.append("end;                                                  ");
	        
	        this.getJdbcTemplate().execute(query.toString());
		}catch(Exception e) {
	        throw new SqlIgnoreException(e.getMessage());
	    }
	}
	public void SP_IF_UPD_CTCTR(String compNo, String userNo) throws SqlIgnoreException{
		try{
			QueryBuffer query = new QueryBuffer();
	        
	        query.append("begin                                                 ");
	        query.append("SP_IF_UPD_CTCTR('"+compNo+"', '"+userNo+"');    		");
	        query.append("end;                                                  ");
	        
	        this.getJdbcTemplate().execute(query.toString());
		}catch(Exception e) {
	        throw new SqlIgnoreException(e.getMessage());
	    }
	}
	public void SP_IF_UPD_DEPT(String compNo, String userNo) throws SqlIgnoreException{
		try{
			QueryBuffer query = new QueryBuffer();
	        
	        query.append("begin                                                 ");
	        query.append("SP_IF_UPD_DEPT('"+compNo+"', '"+userNo+"');    		");
	        query.append("end;                                                  ");
	        
	        this.getJdbcTemplate().execute(query.toString());
		}catch(Exception e) {
	        throw new SqlIgnoreException(e.getMessage());
	    }
	}
	
	
	public List NULL_PASSWORD_USER(String compNo) {
		QueryBuffer query = new QueryBuffer();

	    query.append("SELECT USER_NO			");
	    query.append("FROM TAUSER				");
	    query.append("WHERE password is null	");
	    query.append("  AND  comp_no  =   ?     ");
	    Object[] objects = new Object[] {
	    		compNo
	    };
	    
	    return getJdbcTemplate().queryForList(query.toString(),objects);
	}
	public void SET_PASSWORD(String compNo, String userNo) {
		QueryBuffer query = new QueryBuffer();

	    query.append("UPDATE TAUSER SET             ");
	    query.append("       password          = ?  ");
	    query.append("       ,change_pwd_date  = ?	");
	    query.append("WHERE  1 = 1                  ");
	    query.append("  AND  user_no           = ?  ");
	    query.append("  AND  comp_no           = ?  ");
	    
	    Object[] objects = new Object[] {
	    		CommonUtil.aesEncodeString(userNo),
	    		DateUtil.getDateTime("yyyyMMdd"),
	    		userNo,
	    		compNo
	    };
	    
	    getJdbcTemplate().update(query.toString(), objects);
	}
	@Override
	public List findMailMessageList(String compNo) throws Exception {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("SELECT										");
    	query.append("		messagelist_id	AS messageListId		");
    	query.append("		,description 	AS description			");
    	query.append("		,contents 		AS contents				");
    	query.append("		,receivers 		AS receivers			");
    	query.append("		,retry_cnt		AS retryCnt				");
    	query.append("      ,comp_no        AS compNo               ");
    	query.append("FROM TAMESSAGELIST x							");
    	query.append("WHERE 1=1										");
    	query.append("AND method_type = 'MAIL' 						");
    	query.append("AND message_status IN ('Z','N')				");
    	query.append("AND retry_cnt < 2								");
    	query.getStringEqualQuery("comp_no", compNo);
    	
    	return getJdbcTemplate().queryForList(query.toString());
    }
	@Override
	public int updateMailMessageList(String id, int code, String failMsg) {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("UPDATE TAMESSAGELIST SET							");
    	query.append("	retry_cnt			= NVL(retry_cnt,0) + 1		");
    	query.append("	,send_time			= ? 						");
    	query.append("	,message_status		= ? 						");
    	query.append("	,fail_msg			= ? 						");
    	query.append("WHERE messagelist_id	= ?							");
    	
    	Object[] objects = new Object[] {
    			 DateUtil.getDateTime()
    			 ,code==1?"Y":"N"
    			 ,failMsg
    			 ,id
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    @Override
    public int updateMailMessageRunning(String id)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("UPDATE TAMESSAGELIST SET                          ");
        query.append("  message_status      = ?                         ");
        query.append("WHERE messagelist_id  = ?                         ");
        
        Object[] objects = new Object[] {
                 "R"
                 ,id
        };
        
        return getJdbcTemplate().update(query.toString(), objects);
    }
    
    @Override
    public List getMessageAttachment(String messageListId, String compNo, String objectType)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                                                                    ");
        query.append("       (SELECT value$ FROM TACONFIG WHERE NAME ='FILE_DIR')||                                             ");
        query.append("       nf_file_path||SUBSTR((SELECT value$ FROM TACONFIG WHERE NAME ='FILE_DIR'), LENGTH((SELECT value$ FROM TACONFIG WHERE NAME ='FILE_DIR')))||z.docdata_id  filePath   ");
        query.append("       ,z.file_name fileName                          ");
        query.append("FROM TADOCUMENT x LEFT OUTER JOIN TAOBJDOC y          ");
        query.append("    ON x.comp_no = y.comp_no                          ");
        query.append("    AND x.doc_id = y.doc_id                           ");
        query.append("    INNER JOIN TADOCDATA z ON y.doc_id = z.doc_id     ");
        query.append("WHERE  1  = 1                                         ");
        query.append("  AND  x.comp_no = ?                                  ");
        query.append("  AND  y.object_id = ?                                ");
        query.append("  AND  y.object_type = ?                              ");

        
        Object[] objects = new Object[] {
                compNo
                ,messageListId
                ,objectType
       };
        
        return getJdbcTemplate().queryForList(query.toString(), objects);
    }
}