let $menus := for $menu in doc("Menu.xml")//menu
              order by $menu/@prix ascending
              return $menu

return $menus