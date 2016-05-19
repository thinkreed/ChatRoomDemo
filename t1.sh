git filter-branch --env-filter '
  if [ $GIT_AUTHOR_EMAIL = weijie.hu@qianmo-inc.com ];
          then GIT_AUTHOR_EMAIL=thinkreed2016@gmail.com;
                fi;
                export GIT_AUTHOR_EMAIL'
'
