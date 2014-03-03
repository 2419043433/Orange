A normal db op is just like 
"[select [*|top n] | delete] from table where condition order by attribute"
so we query with code like
result r = client.[query|execute](condition, stopChecker, handler).[asInt|String|Array]