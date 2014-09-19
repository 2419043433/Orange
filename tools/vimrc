set nocompatible
set nu!
colorscheme torte

"syntax enable
if has("syntax")
    syntax on
endif

set showcmd

"文件编码相关
set fileformat=unix

"搜索相关
set hlsearch 
set incsearch

"代码风格统一
set expandtab
set tabstop=4
set shiftwidth=4
set cindent
set softtabstop=4
set backspace=2

filetype plugin on

"new window on the right instead of the left
"set splitright

"状态栏
set ru

"启动窗口位置&大小
winpos 170 60
set lines=50 columns=170

""""""""""""""""""""""""""""""""
"解决编码问题
""""""""""""""""""""""""""""""""
set langmenu=zh_CN.utf-8
set encoding=utf-8
set fileencodings=utf-8,chinese,latin-1
if has("win32")
    set fileencoding=chinese
else
    set fileencoding=utf-8
endif
"解决菜单乱码问题
source $VIMRUNTIME/delmenu.vim
source $VIMRUNTIME/menu.vim
"解决console输出乱码
language messages zh_CN.utf-8

"a.vim 快速切换头和源文件
nnoremap <silent> <F12> :A<CR>
"grep.vim 工程中快速查找
nnoremap <silent> <F3> :Grep<CR>
nnoremap <silent> <F2> :Rgrep<CR>
"grep path (need mingw)
let Grep_Skip_Files = '*.bak *~'
let Grep_Path = '/bin/grep'
let Fgrep_Path = '/bin/fgrep'
let Egrep_Path = '/bin/egrep'
let Agrep_Path = '/bin/agrep'
let Grep_Find_Path = '/usr/bin/find'
let Grep_Xargs_Path = '/usr/bin/xargs'
let Grep_Default_Filelist = '*.c *.cpp *.h'
let Grep_Skip_Dirs = '.svn .git'

"cscopes使用
function UpdateCscope()
    cs kill 0
    silent! execute "!find -name '*.c' -o -name '*.cpp' -o -name '*.cc' -o -name '*.h' -o -name '*.hpp' -o -name '*.java' -type f > tmplist"
    silent! execute "!cscope -bqk -i tmplist"
    silent! execute "!ctags -R -L tmplist"
    silent! execute "!rm -f tmplist"
    cs add cscope.out
endfunction
map <C-F10>c :call UpdateCscope() <CR>


function FormatCode()
    silent! execute "%!astyle --ascii --style=allman --indent=spaces=4 --indent-classes --indent-switches --indent-cases --indent-preprocessor --indent-col1-comments --min-conditional-indent=0 --max-instatement-indent=60 --break-blocks --pad-oper --pad-header --align-pointer=middle --align-reference=type --add-brackets --convert-tabs --lineend=linux"
    endfunction
map <C-S-F> :call FormatCode() <CR>


"set diffexpr=MyDiff()
"function MyDiff()
"  let opt = '-a --binary '
"  if &diffopt =~ 'icase' | let opt = opt . '-i ' | endif
"  if &diffopt =~ 'iwhite' | let opt = opt . '-b ' | endif
"  let arg1 = v:fname_in
"  if arg1 =~ ' ' | let arg1 = '"' . arg1 . '"' | endif
"  let arg2 = v:fname_new
"  if arg2 =~ ' ' | let arg2 = '"' . arg2 . '"' | endif
"  let arg3 = v:fname_out
"  if arg3 =~ ' ' | let arg3 = '"' . arg3 . '"' | endif
"  let eq = ''
"  if $VIMRUNTIME =~ ' '
"    if &sh =~ '\<cmd'
"      let cmd = '""' . $VIMRUNTIME . '\diff"'
"      let eq = '"'
"    else
"      let cmd = substitute($VIMRUNTIME, ' ', '" ', '') . '\diff"'
"    endif
"  else
"    let cmd = $VIMRUNTIME . '\diff'
"  endif
"  silent execute '!' . cmd . ' ' . opt . arg1 . ' ' . arg2 . ' > ' . arg3 . eq
"endfunction

"""""""""""""""""""""""""""""""""
"abandon plusin
""TList插件
"let Tlist_Show_One_File=1
""let Tlist_Exit_OnlyWindow=1
""winmanager
"let g:winManagerWindowLayout='FileExplorer|TagList'
""nmap !m :WMToggle<cr>

