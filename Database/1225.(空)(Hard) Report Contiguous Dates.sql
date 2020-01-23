/****************************************************************************************************
1225. Report Contiguous Dates

Difficulty: Hard

****************************************************************************************************/


select state period_state,
    min(ds) start_date, 
    max(ds) end_date
from (
    select ds, state, 
    row_number() over (partition by state order by ds) seq
    from (
      (select fail_date ds, 'failed' state from Failed where year(fail_date) = 2019)
      union all
      (select success_date ds, 'succeeded' state from Succeeded where year(success_date) = 2019)
    ) a
) b
group by state, dateadd(day, - seq, ds) 
order by start_date


