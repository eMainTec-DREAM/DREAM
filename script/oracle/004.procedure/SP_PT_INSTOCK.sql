/* 
 * 2018-05-25 김정우 수정
 *    수정내용: 입고완료 후 자재 마지막 입고일자 업데이트
 * */
CREATE OR REPLACE procedure SP_PT_INSTOCK(
      v_comp_no                         in varchar2 
     ,v_ptrechist_id                      in number
)is
    v_count                  number(4);  
    
    cursor c_rec_list is
        select 
            comp_no
            ,ptrechist_id
            ,ptrec_mode
            ,ptrec_type
            ,dept_id
            ,wcode_id
            ,rec_date
            ,part_id
            ,DECODE(part_grade,'A','A','B','B','B')  as part_grade
            ,nvl(rec_qty,0) as rec_qty
            ,nvl(unit_price,0) as unit_price
        from TAPTRECHIST
        where 1=1
            and comp_no = v_comp_no
            and ptrechist_id = v_ptrechist_id
        ;
        
    
begin
    
       
       for c1 in c_rec_list loop
           
           if c1.ptrec_mode = 'C' then  -- 입고
               select count(*)
                into v_count
                from taptstock
                where 1=1
                    and comp_no = c1.comp_no
                    and wcode_id =c1.wcode_id
                    and part_id = c1.part_id
                    and part_grade = c1.part_grade
                ;
                   
                if v_count > 0 then
                    update taptstock set
                         stock_qty = nvl(stock_qty,0) + c1.rec_qty
                    where 1=1
                         and comp_no = c1.comp_no
                         and wcode_id =c1.wcode_id
                         and part_id = c1.part_id
                         and part_grade = c1.part_grade
                    ;
                else
                    insert into taptstock(comp_no, wcode_id, part_id, part_grade, stock_qty)
                    values(c1.comp_no, c1.wcode_id, c1.part_id, c1.part_grade, c1.rec_qty)
                    ;
                end if;
                   
                -- 최신단가 갱신
                update taparts set
                    last_price = c1.unit_price
                    ,last_gr_date = to_char(sysdate,'yyyymmdd')
                where comp_no = c1.comp_no
                    and part_id = c1.part_id
                ;
           
           elsif c1.ptrec_mode = 'R' then  -- 취소
               select count(*)
                into v_count
                from taptstock
                where 1=1
                    and comp_no = c1.comp_no
                    and wcode_id =c1.wcode_id
                    and part_id = c1.part_id
                    and part_grade = c1.part_grade
               ;
                   
               if v_count > 0 then
                   update taptstock set
                        stock_qty = nvl(stock_qty,0) - c1.rec_qty
                   where 1=1
                        and comp_no = c1.comp_no
                        and wcode_id =c1.wcode_id
                        and part_id = c1.part_id
                        and part_grade = c1.part_grade
                   ;
               else
                   insert into taptstock(comp_no, wcode_id, part_id, part_grade, stock_qty)
                   values(c1.comp_no, c1.wcode_id, c1.part_id, c1.part_grade, c1.rec_qty * -1)
                   ;
               end if;
           
           end if;
           
       end loop;
       
       

    
end;
/
