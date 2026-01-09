@echo off

REM Swtich to workpath
D:
echo "Start to copy release files ..."
echo.

echo "Start to copy 'Dockerfile' ..."
copy D:\modern-app-poc\inventory-service\src\main\resources\deploy\Dockerfile D:\itau-jar\inventory-service\Dockerfile /y
echo "Copy 'Dockerfile' finished."
echo.
echo.

echo "Start to copy 'inventory-service-kube.yaml' ..."
copy D:\modern-app-poc\inventory-service\src\main\resources\deploy\inventory-service-kube.yaml D:\itau-jar\inventory-service\inventory-service-kube.yaml /y
echo "Copy 'inventory-service-kube.yaml' finished."
echo.
echo.

echo "Start to copy 'inventory-consumer-service-kube.yaml' ..."
copy D:\modern-app-poc\inventory-service\src\main\resources\deploy\inventory-consumer-service-kube.yaml D:\itau-jar\inventory-service\inventory-consumer-service-kube.yaml /y
echo "Copy 'inventory-consumer-service-kube.yaml' finished."
echo.
echo.

echo "Start to copy 'inventory-service-0.0.1-SNAPSHOT.jar' ..."
copy D:\modern-app-poc\inventory-service\target\inventory-service-0.0.1-SNAPSHOT.jar D:\itau-jar\inventory-service\inventory-service-0.0.1-SNAPSHOT.jar /y
echo "Copy 'inventory-service-0.0.1-SNAPSHOT.jar' finished."
echo.
echo.

echo "Start to copy  'package_inventory_service.sh' ..."
copy D:\modern-app-poc\inventory-service\src\main\resources\deploy\package_inventory_service.sh D:\itau-jar\package_inventory_service.sh /y
echo "Copy 'package_inventory_service.sh' finished."
echo.

pause
